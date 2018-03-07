package br.com.ifood.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.ifood.client.OpenWeatherClient;
import br.com.ifood.exception.LimitExceededException;
import br.com.ifood.model.dto.LatLongDTO;
import br.com.ifood.model.dto.WeatherDateDTO;
import br.com.ifood.model.openweather.OpenWeather;
import br.com.ifood.model.responde.ResponseModel;
import br.com.ifood.repository.WeatherRepository;
import br.com.ifood.utils.DateUtils;

@Service
public class OpenWeatherService {

	@Autowired
	private WeatherRepository weatherRepository;
	@Autowired
	private OpenWeatherClient openWeatherClient;

	public ResponseModel getWeatherResponseBy(String cityName) {
		Date currentTime = new Date();
		ResponseModel response = new ResponseModel();

		// it's the first time that this city is being requested
		if (!weatherRepository.getMapCity().containsKey(cityName)) {
			try {
				response = callWeatherServiceBy(cityName);
				weatherRepository.getMapCity().put(cityName, new WeatherDateDTO(response.getTemperature(), currentTime));

			} catch (LimitExceededException e) {
				response.setStatus(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
				response.setCode(HttpStatus.TOO_MANY_REQUESTS.value());
			}

		// this city was requested before
		} else {
			if (DateUtils.addMinutesTo(weatherRepository.getMapCity().get(cityName).getDate(), 10).before(currentTime)) {
				try {
					weatherRepository.getMapCity().put(cityName, new WeatherDateDTO(response.getTemperature(), currentTime));
					weatherRepository.getMapCity().remove(cityName);
					response = callWeatherServiceBy(cityName);

				} catch (LimitExceededException e) {
					response.setStatus(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
					response.setCode(HttpStatus.TOO_MANY_REQUESTS.value());
				}

			} else {
				Double temperature = weatherRepository.getMapCity().get(cityName).getWeather();
				response.setTemperature(temperature);
				response.setMessages(Arrays.asList("RECOVERING PREVIOUSLY REGISTERED WEATHER IN THE LAST 10 MINUTES", "TEMPERATURE: " + temperature));
				response.setStatus(HttpStatus.OK.getReasonPhrase());
				response.setCode(HttpStatus.OK.value());
			}
		}

		return response;
	}

	private ResponseModel callWeatherServiceBy(String cityName) throws LimitExceededException {
		ResponseModel response = new ResponseModel();
		OpenWeather weather;
		List<String> messages = new ArrayList<>();
		Date now = new Date();

		try {
			// if the last request occurred more than 1 minute ago 
			if (DateUtils.addMinutesTo(weatherRepository.getHourLimit(), 1).before(now)) {
				messages.add("IT HAS PASSED MORE THAN 1 MINUTE SINCE LAST CALL");
				messages.add("MAKING NEW REQUEST FOR THE CITY: " + cityName);

				weather = openWeatherClient.getWeatherByCityName(cityName);

				weatherRepository.setHourLimit(now);
				weatherRepository.setRequestsCount(1);

			// if the number of requests is less than the limit for 1 minute
			} else if (weatherRepository.getRequestsCount() < weatherRepository.getLimitCallsPerMinute()) {
				messages.add("MAKING NEW REQUEST FOR THE CITY: " + cityName);
				weatherRepository.setRequestsCount(weatherRepository.getRequestsCount() + 1);

				weather = openWeatherClient.getWeatherByCityName(cityName);

			} else {
				throw new LimitExceededException();
			}

			Double temperature = Double.valueOf(weather.getMain().getTemp());

			messages.add("EXTERNAL API REQUESTS IN THE LAST MINUTE: " + weatherRepository.getRequestsCount());
			messages.add("TEMPERATURE: " + temperature);

			response.setTemperature(temperature);
			response.setStatus(HttpStatus.OK.getReasonPhrase());
			response.setCode(HttpStatus.OK.value());

		} catch (LimitExceededException e) {
			messages.add(e.getMessage());
		}

		response.setMessages(messages);

		return response;
	}

	public ResponseModel getWeatherResponseBy(Double latitude, Double longitude) {
		Date currentTime = new Date();
		ResponseModel response = new ResponseModel();
		LatLongDTO coordinate = new LatLongDTO(latitude, longitude);

		// it's the first time that this latitude and longitude is being requested
		if (!weatherRepository.getMapLatLong().containsKey(coordinate)) {
			try {
				response = callWeatherServiceBy(latitude, longitude);
				weatherRepository.getMapLatLong().put(coordinate, new WeatherDateDTO(response.getTemperature(), currentTime));

			} catch (LimitExceededException e) {
				response.setStatus(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
				response.setCode(HttpStatus.TOO_MANY_REQUESTS.value());
			}

		// this latitude and longitude was requested before
		} else {
			if (DateUtils.addMinutesTo(weatherRepository.getMapLatLong().get(coordinate).getDate(), 10).before(currentTime)) {
				try {
					weatherRepository.getMapLatLong().put(coordinate, new WeatherDateDTO(response.getTemperature(), currentTime));
					weatherRepository.getMapLatLong().remove(coordinate);
					response = callWeatherServiceBy(latitude, longitude);

				} catch (LimitExceededException e) {
					response.setStatus(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
					response.setCode(HttpStatus.TOO_MANY_REQUESTS.value());
				}

			} else {
				Double temperature = weatherRepository.getMapLatLong().get(coordinate).getWeather();
				response.setTemperature(temperature);
				response.setMessages(Arrays.asList("RECOVERING PREVIOUSLY REGISTERED WEATHER IN THE LAST 10 MINUTES", "TEMPERATURE: " + temperature));
				response.setStatus(HttpStatus.OK.getReasonPhrase());
				response.setCode(HttpStatus.OK.value());
			}
		}

		return response;
	}

	private ResponseModel callWeatherServiceBy(Double latitude, Double longitude) throws LimitExceededException {
		ResponseModel response = new ResponseModel();
		OpenWeather weather;
		List<String> messages = new ArrayList<>();
		Date now = new Date();

		try {
			// if the last request occurred more than 1 minute ago 
			if (DateUtils.addMinutesTo(weatherRepository.getHourLimit(), 1).before(now)) {
				messages.add("IT HAS PASSED MORE THAN 1 MINUTE SINCE LAST CALL");
				messages.add("MAKING NEW REQUEST FOR THE LATITUDE " + latitude + " AND LONGITUDE " + longitude);

				weather = openWeatherClient.getWeatherByLatLong(latitude, longitude);

				weatherRepository.setHourLimit(now);
				weatherRepository.setRequestsCount(1);

			// if the number of requests is less than the limit for 1 minute
			} else if (weatherRepository.getRequestsCount() < weatherRepository.getLimitCallsPerMinute()) {
				messages.add("MAKING NEW REQUEST FOR THE LATITUDE " + latitude + " AND LONGITUDE " + longitude);
				weatherRepository.setRequestsCount(weatherRepository.getRequestsCount() + 1);

				weather = openWeatherClient.getWeatherByLatLong(latitude, longitude);

			} else {
				throw new LimitExceededException();
			}

			Double temperature = Double.valueOf(weather.getMain().getTemp());

			messages.add("EXTERNAL API REQUESTS IN THE LAST MINUTE: " + weatherRepository.getRequestsCount());
			messages.add("TEMPERATURE: " + temperature);

			response.setTemperature(temperature);
			response.setStatus(HttpStatus.OK.getReasonPhrase());
			response.setCode(HttpStatus.OK.value());

		} catch (LimitExceededException e) {
			messages.add(e.getMessage());
		}

		response.setMessages(messages);

		return response;
	}

}
