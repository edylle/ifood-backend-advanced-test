package br.com.ifood.propeties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "singleton")
public class SongProperties {

	@Autowired
	private Environment environment;

	private String hot;
	private String average;
	private String chilly;
	private String freezing;

	@PostConstruct
	public void init() {
		hot = environment.getProperty("song.for.hot");
		average = environment.getProperty("song.for.average");
		chilly = environment.getProperty("song.for.chilly");
		freezing = environment.getProperty("song.for.freezing");
	}

	// GETTERS
	public String getHot() {
		return hot;
	}

	public String getAverage() {
		return average;
	}

	public String getChilly() {
		return chilly;
	}

	public String getFreezing() {
		return freezing;
	}

}
