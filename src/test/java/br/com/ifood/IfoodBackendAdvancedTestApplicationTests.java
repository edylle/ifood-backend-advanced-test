package br.com.ifood;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ifood.service.TemperatureService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IfoodBackendAdvancedTestApplicationTests {

	@Test
	public void testTemperature() {
		TemperatureService spotifyService = new TemperatureService();

		assertTrue(spotifyService.isHot(32D));
		assertFalse(spotifyService.isHot(10D));

		assertTrue(spotifyService.isAverage(28D));
		assertFalse(spotifyService.isAverage(14D));

		assertTrue(spotifyService.isChilly(14D));
		assertFalse(spotifyService.isChilly(9D));

		assertTrue(spotifyService.isFreezing(-10D));
		assertFalse(spotifyService.isFreezing(22D));
	}

}
