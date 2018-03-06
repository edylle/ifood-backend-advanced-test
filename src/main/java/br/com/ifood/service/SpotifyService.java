package br.com.ifood.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ifood.client.SpotifyClient;
import br.com.ifood.model.spotify.Items;
import br.com.ifood.model.spotify.Query;
import br.com.ifood.model.spotify.Token;
import br.com.ifood.propeties.SongProperties;
import br.com.ifood.repository.SpotifyRepository;
import br.com.ifood.utils.DateUtils;

@Component
@Scope(scopeName = "singleton")
public class SpotifyService {

	private final Logger logger = LoggerFactory.getLogger(SpotifyService.class);

	private static final Double HOT = 31D;
	private static final Double AVERAGE_MIN = 15D;
	private static final Double AVERAGE_MAX = 30D;
	private static final Double CHILLY_MIN = 10D;
	private static final Double CHILLY_MAX = 14D;
	private static final Double FREEZING = 13D;

	@Autowired
	private SpotifyRepository spotifyRepository;
	@Autowired
	private SpotifyClient spotifyClient;
	@Autowired
	private SongProperties songProperties;

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

		if (isHot(temperature)) {
			query = songProperties.getHot();

		} else if (isAverage(temperature)) {
			query = songProperties.getAverage();

		} else if (isChilly(temperature)) {
			query = songProperties.getChilly();

		} else if (isFreeziing(temperature)) {
			query = songProperties.getFreezing();

		}

		Query queryResult = spotifyClient.getTracksBy(query, getToken());

		for (Items item : queryResult.getTracks().getItems()) {
			result.add(item.getName());
		}

		return result;
	}

	private boolean isHot(Double temperature) {
		return temperature.compareTo(HOT) >= 0;
	}

	private boolean isAverage(Double temperature) {
		return temperature.compareTo(AVERAGE_MIN) >= 0 && temperature.compareTo(AVERAGE_MAX) <= 0;
	}

	private boolean isChilly(Double temperature) {
		return temperature.compareTo(CHILLY_MIN) >= 0 && temperature.compareTo(CHILLY_MAX) <= 0;
	}

	private boolean isFreeziing(Double temperature) {
		return temperature.compareTo(FREEZING) <= 0;
	}
}
