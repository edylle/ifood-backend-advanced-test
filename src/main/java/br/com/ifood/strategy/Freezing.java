package br.com.ifood.strategy;

public class Freezing implements TemperatureStrategy {

	@Override
	public String getSuggestion() {
		return "classical";
	}

}
