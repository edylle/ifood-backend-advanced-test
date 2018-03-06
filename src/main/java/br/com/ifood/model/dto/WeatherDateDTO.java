package br.com.ifood.model.dto;

import java.util.Date;

public class WeatherDateDTO {

	private Double weather;
	private Date date;

	public WeatherDateDTO(Double weather, Date date) {
		this.weather = weather;
		this.date = date;
	}

	// GETTERS AND SETTERS
	public Double getWeather() {
		return weather;
	}

	public void setWeather(Double weather) {
		this.weather = weather;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
