package br.com.ifood.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifood.client.SpotifyClient;
import br.com.ifood.model.spotify.Items;
import br.com.ifood.model.spotify.Query;
import br.com.ifood.model.spotify.Token;
import br.com.ifood.propeties.SongProperties;
import br.com.ifood.repository.SpotifyRepository;
import br.com.ifood.utils.DateUtils;

@Service
public class SpotifyService {

	private final Logger logger = LoggerFactory.getLogger(SpotifyService.class);

	@Autowired
	private SpotifyRepository spotifyRepository;
	@Autowired
	private SpotifyClient spotifyClient;
	@Autowired
	private SongProperties songProperties;
	@Autowired
	private TemperatureService temperatureService;

	public String getToken() {
		Date currentTime = new Date();

		if (spotifyRepository.isTokenValid(currentTime)) {
			return spotifyRepository.getToken();
		}

		logger.info("REFRESHING TOKEN...");
		Token token = spotifyClient.getToken();

		spotifyRepository.setToken(token.getAccess_token());
		spotifyRepository.setTimeLimit(DateUtils.addSecondsTo(currentTime, token.getExpires_in()));

		return token.getAccess_token();
	}

	public List<String> getTracksBy(Double temperature) {
		List<String> result = new ArrayList<>();
		String query = "";

		if (temperatureService.isHot(temperature)) {
			query = songProperties.getHot();

		} else if (temperatureService.isAverage(temperature)) {
			query = songProperties.getAverage();

		} else if (temperatureService.isChilly(temperature)) {
			query = songProperties.getChilly();

		} else if (temperatureService.isFreezing(temperature)) {
			query = songProperties.getFreezing();

		}

		Query queryResult = spotifyClient.getTracksBy(query, getToken());

		for (Items item : queryResult.getTracks().getItems()) {
			result.add(item.getName());
		}

		return result;
	}


}
