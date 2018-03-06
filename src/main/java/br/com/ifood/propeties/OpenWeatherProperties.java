package br.com.ifood.propeties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "singleton")
public class OpenWeatherProperties {

	@Autowired
	private Environment environment;

	private String url;
	private String units;
	private String appId;
	private Integer limitCallsPerMinute;

	@PostConstruct
	public void init() {
		url = environment.getProperty("openweather.api.url");
		units = environment.getProperty("openweather.api.units");
		appId = environment.getProperty("openweather.api.appid");
		limitCallsPerMinute = Integer.valueOf(environment.getProperty("openweather.api.limit.calls.per.minute"));
	}

	// GETTERS
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getLimitCallsPerMinute() {
		return limitCallsPerMinute;
	}

}
