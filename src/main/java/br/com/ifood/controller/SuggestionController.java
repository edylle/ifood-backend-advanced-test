package br.com.ifood.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifood.model.responde.ResponseModel;
import br.com.ifood.repository.WeatherCallRepository;

@RestController
@RequestMapping("/suggestion")
public class SuggestionController {

	private final Logger logger = LoggerFactory.getLogger(SuggestionController.class);
	
	@Autowired
	private WeatherCallRepository weatherCallRepository;
	
	@GetMapping("/city")
	public ResponseModel findByCityName(@RequestParam(value = "name") String name) {
		try {
			return weatherCallRepository.getWeatherBy(name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ResponseModel response = new ResponseModel();

			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
			response.setMessages(Arrays.asList("Unknown error"));
			
			return response;
		}
	}

	@GetMapping("/coordinates")
	public ResponseModel findByCoordinates(@RequestParam(value = "lat") Double latitude,
										  @RequestParam(value = "long") Double longitude) {

		try {
			return weatherCallRepository.getWeatherBy(latitude, longitude);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ResponseModel response = new ResponseModel();

			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
			response.setMessages(Arrays.asList("Unknown error"));
			
			return response;
		}
	}

}
