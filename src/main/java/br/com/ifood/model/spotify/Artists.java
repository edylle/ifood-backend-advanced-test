package br.com.ifood.model.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Artists {

	private String id;
	private External_urls external_urls;
	private String name;
	private String type;
	private String uri;
	private String href;

	// GETTERS AND SETTERS
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public External_urls getExternal_urls() {
		return external_urls;
	}

	public void setExternal_urls(External_urls external_urls) {
		this.external_urls = external_urls;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
