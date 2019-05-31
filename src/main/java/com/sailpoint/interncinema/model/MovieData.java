package com.sailpoint.interncinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

public class MovieData {

	private String _title;

	public MovieData() {
	}

	public MovieData(String title) {
		this._title = title;
	}

	@JsonProperty("title")
	public String get_title() {
		return this._title;
	}

	@JsonProperty("title")
	public void set_title(String title) {
		this._title = title;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}