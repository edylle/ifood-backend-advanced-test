package br.com.ifood;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ifood.strategy.TemperatureContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IfoodBackendAdvancedTestApplicationTests {

	@Test
	public void testTemperature() {

		assertSame("party", new TemperatureContext(32D).getSuggestion());
		assertNotSame("party", new TemperatureContext(10D).getSuggestion());

		assertSame("pop", new TemperatureContext(28D).getSuggestion());
		assertNotSame("pop", new TemperatureContext(14D).getSuggestion());

		assertSame("rock", new TemperatureContext(14D).getSuggestion());
		assertNotSame("rock", new TemperatureContext(9D).getSuggestion());

		assertSame("classical", new TemperatureContext(-10D).getSuggestion());
		assertNotSame("classical", new TemperatureContext(22D).getSuggestion());

	}

}
