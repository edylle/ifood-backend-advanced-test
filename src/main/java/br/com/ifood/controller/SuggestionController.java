package br.com.ifood.controller;

import java.text.Normalizer;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import br.com.ifood.facade.SuggestionFacade;
import br.com.ifood.model.responde.ResponseModel;

@RestController
@RequestMapping("/suggestion")
public class SuggestionController {

	private final Logger logger = LoggerFactory.getLogger(SuggestionController.class);

	@Autowired
	private SuggestionFacade suggestionFacade;

	@GetMapping("/city")
	public ResponseModel findByCityName(@RequestParam(value = "name") String name) {
		try {
			return suggestionFacade.getTracksBy(nomalizeText(name));

		} catch (HttpClientErrorException e) {
			logger.error(e.getMessage(), e);
			ResponseModel response = new ResponseModel();

			response.setCode(HttpStatus.NOT_FOUND.value());
			response.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
			response.setMessages(Arrays.asList(e.getMessage()));

			return response;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ResponseModel response = new ResponseModel();

			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
			response.setMessages(Arrays.asList("UNKNOWN ERROR"));

			return response;
		}
	}

	@GetMapping("/coordinates")
	public ResponseModel findByCoordinates(@RequestParam(value = "lat") Double latitude,
										  @RequestParam(value = "lon") Double longitude) {
		try {
			return suggestionFacade.getTracksBy(latitude, longitude);

		} catch (HttpClientErrorException e) {
			logger.error(e.getMessage(), e);
			ResponseModel response = new ResponseModel();

			response.setCode(HttpStatus.NOT_FOUND.value());
			response.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
			response.setMessages(Arrays.asList(e.getMessage()));

			return response;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ResponseModel response = new ResponseModel();

			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
			response.setMessages(Arrays.asList("UNKNOWN ERROR"));

			return response;
		}
	}

	private String nomalizeText(String text) {
		if (text == null) {
			return "";
		}

		String normalizedText = text;
		normalizedText = Normalizer.normalize(text, Normalizer.Form.NFD);
		return normalizedText.replaceAll("[^\\p{ASCII}]", "");
	}

}
