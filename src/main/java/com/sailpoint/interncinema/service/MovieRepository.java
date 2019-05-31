package com.sailpoint.interncinema.service;

import com.google.inject.Singleton;
import com.sailpoint.interncinema.model.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class MovieRepository implements Repository<Movie> {

	private static Long _idCounter = 0L;
	private static Map<Long, Movie> _inMemoryMovies = new HashMap<>();

	public MovieRepository() {
	}

	@Override
	public List<Movie> getAll() {
		return new ArrayList<>(_inMemoryMovies.values());
	}

	@Override
	public Movie get(Long id) {
		return _inMemoryMovies.get(id);
	}

	@Override
	public Long add(Movie item) {
		Long id = getNextId();
		item.setId(id);
		_inMemoryMovies.put(id, item);

		return id;
	}

	@Override
	public Movie update(Movie item) {
		Long id = item.getId();
		_inMemoryMovies.put(id, item);

		return _inMemoryMovies.get(id);
	}

	@Override
	public Movie delete(Movie item) {
		return _inMemoryMovies.remove(item.getId());
	}

	private static Long getNextId() {
		_idCounter++;

		return _idCounter;
	}
}