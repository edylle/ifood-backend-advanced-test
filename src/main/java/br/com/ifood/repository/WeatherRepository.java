package br.com.ifood.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.com.ifood.model.dto.LatLongDTO;
import br.com.ifood.model.dto.WeatherDateDTO;
import br.com.ifood.propeties.OpenWeatherProperties;

@Repository
@Scope(scopeName = "singleton")
public class WeatherRepository {

	@Autowired
	private OpenWeatherProperties openWeatherProperties;

	private Date hourLimit;
	private Integer requestsCount;
	private Map<String, WeatherDateDTO> mapCity;
	private Map<LatLongDTO, WeatherDateDTO> mapLatLong;

	@PostConstruct
	public void init() {
		hourLimit = new Date();
		requestsCount = 0;
		mapCity = new HashMap<>();
		mapLatLong = new HashMap<>();
	}

	// GETTERS AND SETTERS
	public Date getHourLimit() {
		return hourLimit;
	}

	public void setHourLimit(Date hourLimit) {
		this.hourLimit = hourLimit;
	}

	public Integer getRequestsCount() {
		return requestsCount;
	}

	public void setRequestsCount(Integer requestsCount) {
		this.requestsCount = requestsCount;
	}

	public Map<String, WeatherDateDTO> getMapCity() {
		return mapCity;
	}

	public void setMapCity(Map<String, WeatherDateDTO> mapCity) {
		this.mapCity = mapCity;
	}

	public Map<LatLongDTO, WeatherDateDTO> getMapLatLong() {
		return mapLatLong;
	}

	public void setMapLatLong(Map<LatLongDTO, WeatherDateDTO> mapLatLong) {
		this.mapLatLong = mapLatLong;
	}

	public Integer getLimitCallsPerMinute() {
		return openWeatherProperties.getLimitCallsPerMinute();
	}

}
