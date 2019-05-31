package com.sailpoint.interncinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

public class Movie {

	private Long _id;
	private MovieData _movieData;

	public Movie() {
	}

	public Movie(Long id, MovieData movieData) {
		this._id = id;
		this._movieData = movieData;
	}

	@JsonProperty("id")
	public Long getId() {
		return this._id;
	}

	@JsonProperty("movieData")
	public MovieData getMovieData() {
		return this._movieData;
	}

	@JsonProperty("id")
	public void setId(Long id) {
		this._id = id;
	}

	@JsonProperty("movieData")
	public void setMovieData(MovieData movieData) {
		this._movieData = movieData;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}