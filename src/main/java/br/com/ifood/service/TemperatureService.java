package br.com.ifood.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(scopeName = "singleton")
public class TemperatureService {

	private static final Double HOT = 31D;
	private static final Double AVERAGE_MIN = 15D;
	private static final Double AVERAGE_MAX = 30D;
	private static final Double CHILLY_MIN = 10D;
	private static final Double CHILLY_MAX = 14D;
	private static final Double FREEZING = 13D;

	public boolean isHot(Double temperature) {
		return temperature.compareTo(HOT) >= 0;
	}

	public boolean isAverage(Double temperature) {
		return temperature.compareTo(AVERAGE_MIN) >= 0 && temperature.compareTo(AVERAGE_MAX) <= 0;
	}

	public boolean isChilly(Double temperature) {
		return temperature.compareTo(CHILLY_MIN) >= 0 && temperature.compareTo(CHILLY_MAX) <= 0;
	}

	public boolean isFreezing(Double temperature) {
		return temperature.compareTo(FREEZING) <= 0;
	}

}
