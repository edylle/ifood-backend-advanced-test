package br.com.ifood.facade;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.ifood.model.responde.ResponseModel;
import br.com.ifood.service.OpenWeatherService;
import br.com.ifood.service.SpotifyService;

@Component
public class SuggestionFacade {

	private final Logger logger = LoggerFactory.getLogger(SuggestionFacade.class);

	@Autowired
	private Environment environment;

	@Autowired
	private OpenWeatherService openWeatherService;
	@Autowired
	private SpotifyService spotifyService;

	@HystrixCommand(fallbackMethod = "cityNameFallback")
	public ResponseModel getTracksBy(String cityName) {
		ResponseModel response = openWeatherService.getWeatherResponseBy(cityName);
		getSpotifyTracks(response);

		return response;
	}

	@HystrixCommand(fallbackMethod = "latitudeLongitudeFallback")
	public ResponseModel getTracksBy(Double latitude, Double longitude) {
		ResponseModel response = openWeatherService.getWeatherResponseBy(latitude, longitude);
		getSpotifyTracks(response);

		return response;
	}

	private void getSpotifyTracks(ResponseModel response) {
		List<String> tracks = spotifyService.getTracksBy(response.getTemperature());
		response.setResult(tracks);
	}

	public ResponseModel cityNameFallback(String cityName) {
		logger.error("CALLING FALLBACK METHOD FOR THE ARGUMENTS: " + cityName);

		return defaultResponse();
	}

	public ResponseModel latitudeLongitudeFallback(Double latitude, Double longitude) {
		logger.error("CALLING FALLBACK METHOD FOR THE ARGUMENTS: " + latitude + " " + longitude);

		return defaultResponse();
	}

	private ResponseModel defaultResponse() {
		ResponseModel response = new ResponseModel();
		List<String> messages = new ArrayList<>();
		List<String> tracks = new ArrayList<>();

		Integer timeout = Integer.valueOf(environment.getProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds"));

		messages.add("IT LOOKS LIKE THAT EXTERNAL APIs ARE OUT OF SERVICE");
		messages.add("MAX TIMEOUT ACCEPTED (IN SECONDS): " + (timeout / 1000));
		messages.add("RETURNING EDYLLE's TRACKS SUGGESTIONS INSTEAD");

		tracks.add("Staiway to Heaven");
		tracks.add("Sultans of Swing");
		tracks.add("The Thrill is Gone");
		tracks.add("All Along the Watchtower");
		tracks.add("Europa");
		tracks.add("Fur Elise");
		tracks.add("Serrado");
		tracks.add("Bohemian Rhapsody");
		tracks.add("Part-Time Lover");
		tracks.add("Billie Jean");
		tracks.add("Comfortably Numb");

		response.setCode(HttpStatus.OK.value());
		response.setStatus(HttpStatus.OK.getReasonPhrase());
		response.setMessages(messages);
		response.setResult(tracks);

		return response;
	}

}
