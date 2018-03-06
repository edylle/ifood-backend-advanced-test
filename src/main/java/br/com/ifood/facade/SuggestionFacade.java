package br.com.ifood.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ifood.model.responde.ResponseModel;
import br.com.ifood.service.OpenWeatherService;
import br.com.ifood.service.SpotifyService;

@Component
public class SuggestionFacade {

	@Autowired
	private OpenWeatherService openWeatherService;
	@Autowired
	private SpotifyService spotifyService;

	public ResponseModel getTracksBy(String cityName) {
		ResponseModel response = openWeatherService.getWeatherResponseBy(cityName);
		getSpotifyTracks(response);

		return response;
	}

	public ResponseModel getTracksBy(Double latitude, Double longitude) {
		ResponseModel response = openWeatherService.getWeatherResponseBy(latitude, longitude);
		getSpotifyTracks(response);

		return response;
	}

	private void getSpotifyTracks(ResponseModel response) {
		List<String> tracks = spotifyService.getTracksBy(response.getTemperature());
		response.setResult(tracks);
	}

}
