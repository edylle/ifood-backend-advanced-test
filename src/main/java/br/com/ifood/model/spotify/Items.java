package br.com.ifood.model.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {

	private External_urls external_urls;
	private String[] available_markets;
	private String preview_url;
	private External_ids external_ids;
	private Album album;
	private String duration_ms;
	private String type;
	private String uri;
	private String track_number;
	private String id;
	private Artists[] artists;
	private String disc_number;
	private String explicit;
	private String name;
	private String href;
	private String popularity;

	// GETTERS AND SETTERS
	public External_urls getExternal_urls() {
		return external_urls;
	}

	public void setExternal_urls(External_urls external_urls) {
		this.external_urls = external_urls;
	}

	public String[] getAvailable_markets() {
		return available_markets;
	}

	public void setAvailable_markets(String[] available_markets) {
		this.available_markets = available_markets;
	}

	public String getPreview_url() {
		return preview_url;
	}

	public void setPreview_url(String preview_url) {
		this.preview_url = preview_url;
	}

	public External_ids getExternal_ids() {
		return external_ids;
	}

	public void setExternal_ids(External_ids external_ids) {
		this.external_ids = external_ids;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getDuration_ms() {
		return duration_ms;
	}

	public void setDuration_ms(String duration_ms) {
		this.duration_ms = duration_ms;
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

	public String getTrack_number() {
		return track_number;
	}

	public void setTrack_number(String track_number) {
		this.track_number = track_number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Artists[] getArtists() {
		return artists;
	}

	public void setArtists(Artists[] artists) {
		this.artists = artists;
	}

	public String getDisc_number() {
		return disc_number;
	}

	public void setDisc_number(String disc_number) {
		this.disc_number = disc_number;
	}

	public String getExplicit() {
		return explicit;
	}

	public void setExplicit(String explicit) {
		this.explicit = explicit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getPopularity() {
		return popularity;
	}

	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}

}
