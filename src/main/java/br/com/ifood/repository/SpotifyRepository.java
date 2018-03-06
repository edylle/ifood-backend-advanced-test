package br.com.ifood.repository;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(scopeName = "singleton")
public class SpotifyRepository {

	private String token;
	private Date timeLimit;

	@PostConstruct
	public void init() {
		timeLimit = new Date();
	}

	public boolean isTokenValid(Date currentTime) {
		return currentTime.before(timeLimit);
	}

	// GETTERS AND SETTERS
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Date timeLimit) {
		this.timeLimit = timeLimit;
	}

}
