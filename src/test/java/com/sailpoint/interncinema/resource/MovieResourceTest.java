package com.sailpoint.interncinema.resource;

import com.google.inject.Guice;
import com.google.inject.Injector;

import com.sailpoint.interncinema.conf.MovieRepositoryModule;
import com.sailpoint.interncinema.model.Movie;
import com.sailpoint.interncinema.model.MovieData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class MovieResourceTest {
	private Injector injector;

	@Before
	public void setUp() throws Exception {
		injector = Guice.createInjector(new MovieRepositoryModule());
	}

	@After
	public void tearDown() throws Exception {
		injector = null;
	}

	@Test
	public void testMovieResource() {
		getAllMoviesTest();
		addMovieTest();
		updateMovieTest();
		deleteMovieTest();
	}

	private void getAllMoviesTest() {
		MovieResource movieResource = injector.getInstance(MovieResource.class);

		/* List of 'movie' should return empty on startup. */
		ArrayList<Movie> fakeMovies = new ArrayList<>();
		fakeMovies.add(new Movie(2L, new MovieData("title")));
		assertNotEquals(fakeMovies, movieResource.getAllMovies());

		assertEquals(new ArrayList<Movie>(), movieResource.getAllMovies());
	}

	private void addMovieTest() {
		MovieResource movieResource = injector.getInstance(MovieResource.class);

		Response expectedResponse = Response.status(Response.Status.CREATED).build();

		/* Adding valid 'movie' should return status CREATED. */
		Movie fakeMovie = new Movie(null, new MovieData("Fake Movie"));
		assertEquals(expectedResponse.getStatus(), movieResource.addMovie(fakeMovie).getStatus());

		/* Adding null 'movie' should return status BAD_REQUEST. */
		fakeMovie = null;
		expectedResponse = Response.status(Response.Status.BAD_REQUEST).build();
		assertEquals(expectedResponse.getStatus(), movieResource.addMovie(fakeMovie).getStatus());
	}

	private void updateMovieTest() {
		MovieResource movieResource = injector.getInstance(MovieResource.class);

		Response expectedResponse = Response.status(Response.Status.OK).build();

		/* Add a fake 'movie'. */
		Movie fakeMovie = new Movie(null, new MovieData("Fake Movie"));
		movieResource.addMovie(fakeMovie);

		/* Updating an existing 'movie' should return status OK. */
		fakeMovie = new Movie(1L, new MovieData("Updated Movie"));
		assertEquals(expectedResponse.getStatus(), movieResource.updateMovie(fakeMovie).getStatus());

		/* Title of the updated 'movie' should match. */
		assertEquals("Updated Movie", movieResource.getAllMovies().get(0).getMovieData().get_title());

		/* Updating null 'movie' should return status BAD_REQUEST. */
		fakeMovie = null;
		expectedResponse = Response.status(Response.Status.BAD_REQUEST).build();
		assertEquals(expectedResponse.getStatus(), movieResource.updateMovie(fakeMovie).getStatus());
	}

	private void deleteMovieTest() {
		MovieResource movieResource = injector.getInstance(MovieResource.class);

		Response expectedResponse = Response.status(Response.Status.OK).build();

		/* Add a fake 'movie'. */
		Movie fakeMovie = new Movie(null, new MovieData("Fake Movie"));
		movieResource.addMovie(fakeMovie);

		/* Deleting existing 'movie' should return status OK. */
		for (Movie movie : movieResource.getAllMovies())
			assertEquals(expectedResponse.getStatus(), movieResource.deleteMovie(movie.getId()).getStatus());

		/* Deleting null 'movie' should return status BAD_REQUEST. */
		fakeMovie = null;
		expectedResponse = Response.status(Response.Status.BAD_REQUEST).build();
		assertEquals(expectedResponse.getStatus(), movieResource.deleteMovie(null).getStatus());

		/* List of 'movie' should be empty now. */
		assertEquals(new ArrayList<Movie>(), movieResource.getAllMovies());
	}
}