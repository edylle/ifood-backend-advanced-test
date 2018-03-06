package br.com.ifood.strategy;

public class Hot implements TemperatureStrategy {

	@Override
	public String getSuggestion() {
		return "party";
	}

}
