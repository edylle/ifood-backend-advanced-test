package br.com.ifood.model.openweather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Clouds {

	private String all;

	// GETTERS AND SETTERS
	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		this.all = all;
	}

}
