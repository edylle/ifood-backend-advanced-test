package br.com.ifood.model.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class External_urls {

	private String spotify;

	// GETTERS AND SETTERS
	public String getSpotify() {
		return spotify;
	}

	public void setSpotify(String spotify) {
		this.spotify = spotify;
	}

}
