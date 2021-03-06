package com.sailpoint.interncinema.service;

import com.sailpoint.interncinema.model.Movie;
import com.sailpoint.interncinema.model.MovieData;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class MovieRepositoryTest {

	MovieRepository _movieRepository;

	@Before
	public void setUp() throws Exception {
		_movieRepository = new MovieRepository();
	}

	@Test
	public void testgetAll() {
		List<Movie> expected = new ArrayList<>();
		List<Movie> movies = _movieRepository.getAll();

		assertNotNull(movies);
		assertEquals(expected, movies);
	}

	@Test
	public void testAdd() {
		Movie movie = new Movie(1L, new MovieData("title"));

		List<Movie> expected = new ArrayList<>();
		expected.add(movie);

		_movieRepository.add(movie);
		List<Movie> movies = _movieRepository.getAll();

		assertNotNull(movies);
		assertEquals(expected, movies);
	}

	@Test
	public void testGet() {
		// TODO
	}

	@Test
	public void testUpdate() {
		// TODO
	}

	@Test
	public void testDelete() {
		// TODO
	}

}
