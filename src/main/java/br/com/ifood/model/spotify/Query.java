package br.com.ifood.model.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Query {

	private Tracks tracks;

	// GETTERS AND SETTERS
	public Tracks getTracks() {
		return tracks;
	}

	public void setTracks(Tracks tracks) {
		this.tracks = tracks;
	}

}
