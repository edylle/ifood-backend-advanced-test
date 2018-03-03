package br.com.ifood.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.ifood.exception.LimitExceededException;
import br.com.ifood.model.dto.LatLongDTO;
import br.com.ifood.model.dto.WeatherDateDTO;
import br.com.ifood.model.responde.ResponseModel;

@Service
public class WeatherCallRepository {

	private static final Integer LIMIT_CALLS_PER_MINUTE = 60;

	private static Date hourLimit = new Date();
	private static Integer requestsCount = 0;
	private static Map<String, WeatherDateDTO> mapCity = new HashMap<>();
	private static Map<LatLongDTO, WeatherDateDTO> mapLatLong = new HashMap<>();

	public ResponseModel getWeatherBy(String cityName) {
		return beforeCallByCity(cityName);
	}

	public ResponseModel getWeatherBy(Double latitude, Double longitude) {
		return beforeCallByLatLong(latitude, longitude);
	}

	@SuppressWarnings("null")
	private ResponseModel beforeCallByCity(String cityName) {
		ResponseModel response = new ResponseModel();
		if (!mapCity.containsKey(cityName)) {
			try {
				response = callBy(cityName);
				mapCity.put(cityName, new WeatherDateDTO((Integer) response.getResult(), new Date()));

			} catch (LimitExceededException e) {
				response.setStatus(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
				response.setCode(HttpStatus.TOO_MANY_REQUESTS.value());
			}

		} else {
			if (addMinutesTo(mapCity.get(cityName).getDate(), 10).before(new Date())) {
				try {
					response = callBy(cityName);

				} catch (LimitExceededException e) {
					response.setStatus(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
					response.setCode(HttpStatus.TOO_MANY_REQUESTS.value());
				}

			} else {
				response.setResult(mapCity.get(cityName).getWeather());
				response.setMessages(Arrays.asList("RECUPERANDO CLIMA PREVIAMENTE CADASTRADO NOS ULTIMOS 10 MINUTOS"));
				response.setStatus(HttpStatus.OK.getReasonPhrase());
				response.setCode(HttpStatus.OK.value());
			}
		}

		return response;
	}

	@SuppressWarnings("null")
	private ResponseModel beforeCallByLatLong(Double latitude, Double longitude) {
		LatLongDTO coordinate = new LatLongDTO(latitude, longitude);
		ResponseModel response = new ResponseModel();
		if (!mapLatLong.containsKey(coordinate)) {
			try {
				response = callBy(latitude, longitude);
				mapLatLong.put(coordinate, new WeatherDateDTO((Integer) response.getResult(), new Date()));

			} catch (LimitExceededException e) {
				response.setStatus(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
				response.setCode(HttpStatus.TOO_MANY_REQUESTS.value());
			}

		} else {
			if (addMinutesTo(mapLatLong.get(coordinate).getDate(), 10).before(new Date())) {
				try {
					response = callBy(latitude, longitude);

				} catch (LimitExceededException e) {
					response.setStatus(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
					response.setCode(HttpStatus.TOO_MANY_REQUESTS.value());
				}

			} else {
				response.setResult(mapLatLong.get(coordinate).getWeather());
				response.setMessages(Arrays.asList("RECUPERANDO CLIMA PREVIAMENTE CADASTRADO NOS ULTIMOS 10 MINUTOS"));
				response.setStatus(HttpStatus.OK.getReasonPhrase());
				response.setCode(HttpStatus.OK.value());
			}
		}

		return response;
	}

	private ResponseModel callBy(String cityName) throws LimitExceededException {
		ResponseModel response = new ResponseModel();
		List<String> messages = new ArrayList<>();
		Date now = new Date();

		try {
			if (addMinutesTo(hourLimit, 1).before(now)) {
				messages.add("FAZENDO REQUISICAO PELO NOME: " + cityName
						+ " ==> FAZ MAIS DE 1 MINUTOS DESDE A ULTIMA CHAMADA");
				hourLimit = now;
				requestsCount = 0;

			} else {
				if (requestsCount < LIMIT_CALLS_PER_MINUTE) {
					messages.add("FAZENDO REQUISICAO PELO NOME: " + cityName);
					requestsCount++;

				} else {
					throw new LimitExceededException();
				}
			}

			messages.add("QUANTIDADE DE CHAMADAS NO ULTIMO MINUTO: " + requestsCount);

			response.setResult(30);
			response.setStatus(HttpStatus.OK.getReasonPhrase());
			response.setCode(HttpStatus.OK.value());

		} catch (LimitExceededException e) {
			messages.add(e.getMessage());
		}

		response.setMessages(messages);

		return response;
	}

	private ResponseModel callBy(Double latitude, Double longitude) throws LimitExceededException {
		ResponseModel response = new ResponseModel();
		List<String> messages = new ArrayList<>();
		Date now = new Date();

		try {
			if (addMinutesTo(hourLimit, 1).before(now)) {
				messages.add("FAZENDO REQUISICAO ==> FAZ MAIS DE 1 MINUTOS DESDE A ULTIMA CHAMADA");
				hourLimit = now;
				requestsCount = 0;

			} else {
				if (requestsCount < LIMIT_CALLS_PER_MINUTE) {
					messages.add("FAZENDO REQUISICAO");
					requestsCount++;

				} else {
					throw new LimitExceededException();
				}
			}

			messages.add("QUANTIDADE DE CHAMADAS NO ULTIMO MINUTO: " + requestsCount);

			response.setResult(23);
			response.setStatus(HttpStatus.OK.getReasonPhrase());
			response.setCode(HttpStatus.OK.value());

		} catch (LimitExceededException e) {
			messages.add(e.getMessage());
		}

		response.setMessages(messages);

		return response;
	}

	public static Date addMinutesTo(Date date, int minutes) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutes);

		return cal.getTime();
	}

}
