package br.com.ifood.strategy;

public class Chilly implements TemperatureStrategy {

	@Override
	public String getSuggestion() {
		return "rock";
	}

}
