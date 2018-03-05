package br.com.ifood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ifood.model.openweather.OpenWeather;
import br.com.ifood.propeties.OpenWeatherProperties;

@Service
public class OpenWeatherService {

	@Autowired
	private OpenWeatherProperties openWeatherProperties;

	public OpenWeather getWeatherByCityName(String name) {
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(openWeatherProperties.getUrl());

		builder.queryParam("q", name);
		builder.queryParam("units", openWeatherProperties.getUnits());
		builder.queryParam("APPID", openWeatherProperties.getAppId());

		OpenWeather response = restTemplate.getForObject(builder.buildAndExpand().toUri(), OpenWeather.class);

		return response;
	}

	public OpenWeather getWeatherByLatLong(Double latitude, Double longitude) {
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(openWeatherProperties.getUrl());

		builder.queryParam("lat", latitude);
		builder.queryParam("long", longitude);
		builder.queryParam("units", openWeatherProperties.getUnits());
		builder.queryParam("APPID", openWeatherProperties.getAppId());

		OpenWeather response = restTemplate.getForObject(builder.buildAndExpand().toUri(), OpenWeather.class);

		return response;
	}

}
