package br.com.ifood.strategy;

public class Average implements TemperatureStrategy {

	@Override
	public String getSuggestion() {
		return "pop";
	}

}
