package br.com.ifood.model.responde;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResponseModel {

	@JsonIgnore
	private Double temperature;

	private String status;
	private Integer code;
	private List<String> messages;
	private Object result;

	// GETTERS AND SETTERS
	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
