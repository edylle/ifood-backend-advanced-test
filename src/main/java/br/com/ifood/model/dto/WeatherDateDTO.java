package br.com.ifood.model.dto;

import java.util.Date;

public class WeatherDateDTO {

	private Integer weather;
	private Date date;

	public WeatherDateDTO(Integer weather, Date date) {
		this.weather = weather;
		this.date = date;
	}

	// GETTERS AND SETTERS
	public Integer getWeather() {
		return weather;
	}

	public void setWeather(Integer weather) {
		this.weather = weather;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
