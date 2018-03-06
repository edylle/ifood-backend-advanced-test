package br.com.ifood.strategy;

public class TemperatureContext {

	private static final Double HOT = 31D;
	private static final Double AVERAGE_MIN = 15D;
	private static final Double AVERAGE_MAX = 30D;
	private static final Double CHILLY_MIN = 10D;
	private static final Double CHILLY_MAX = 14D;

	private Double temperature;

	public TemperatureContext(Double temperature) {
		if (temperature == null) {
			temperature = 0D;
		}

		this.temperature = temperature;
	}

	public String getSuggestion() {
		if (isHot())
			return new Hot().getSuggestion();

		else if (isAverage())
			return new Average().getSuggestion();

		else if (isChilly())
			return new Chilly().getSuggestion();

		else
			return new Freezing().getSuggestion();
	}

	private boolean isHot() {
		return temperature.compareTo(HOT) >= 0;
	}

	private boolean isAverage() {
		return temperature.compareTo(AVERAGE_MIN) >= 0 && temperature.compareTo(AVERAGE_MAX) <= 0;
	}

	private boolean isChilly() {
		return temperature.compareTo(CHILLY_MIN) >= 0 && temperature.compareTo(CHILLY_MAX) <= 0;
	}

}
