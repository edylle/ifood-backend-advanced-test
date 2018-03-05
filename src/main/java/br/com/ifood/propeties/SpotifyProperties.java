package br.com.ifood.propeties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "singleton")
public class SpotifyProperties {

	@Autowired
	private Environment environment;

	private String tokenUrl;
	private String searchUrl;
	private String type;
	private String market;
	private String limit;
	private String clientID;
	private String clientSecret;

	@PostConstruct
	public void init() {
		tokenUrl = environment.getProperty("spotify.api.token.url");
		searchUrl = environment.getProperty("spotify.api.search.url");
		type = environment.getProperty("spotify.api.type");
		market = environment.getProperty("spotify.api.market");
		limit = environment.getProperty("spotify.api.limit");
		clientID = environment.getProperty("spotify.api.client.id");
		clientSecret = environment.getProperty("spotify.api.client.secret");
	}

	// GETTERS
	public String getTokenUrl() {
		return tokenUrl;
	}

	public String getSearchUrl() {
		return searchUrl;
	}

	public String getType() {
		return type;
	}

	public String getMarket() {
		return market;
	}

	public String getLimit() {
		return limit;
	}

	public String getClientID() {
		return clientID;
	}

	public String getClientSecret() {
		return clientSecret;
	}

}
