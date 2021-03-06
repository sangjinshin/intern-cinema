package com.sailpoint.interncinema.resource;

import com.sailpoint.interncinema.model.Movie;
import com.sailpoint.interncinema.model.MovieData;
import com.sailpoint.interncinema.service.Repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@SuppressWarnings("unchecked")
public class MovieResourceTest {

	@InjectMocks
	MovieResource _movieResource;

	@Mock
	Repository _mockRepository;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void getAllMoviesTest() {
		List<Movie> movies = new ArrayList<>();
		when(_mockRepository.getAll()).thenReturn(movies);

		List<Movie> result = _movieResource.getAllMovies();

		verify(_mockRepository).getAll();
		assertNotNull(result);
		assertEquals(movies, result);
	}

	@Test
	public void addValidMovieTest() {
		Movie movie = new Movie(null, new MovieData("Title"));
		Long id = 1L;

		when(_mockRepository.add(movie)).thenReturn(id);

		Response result = _movieResource.addMovie(movie);

		assertNotNull(result);
		assertEquals(result.getEntity(), null);
		assertEquals(Response.status(Response.Status.CREATED).build().getStatus(), result.getStatus());
	}

	@Test
	public void addNullMovieTest() {
		Movie movie = null;
		Response result = _movieResource.addMovie(movie);

		assertNotNull(result);
		assertEquals(Response.status(Response.Status.BAD_REQUEST).build().getStatus(), result.getStatus());
	}

	@Test
	public void updateValidMovieTest() {
		Long id = 1L;
		Movie movie = new Movie(null, new MovieData("Title"));
		Movie updatedMovie = new Movie(id, new MovieData("New Title"));

		when(_mockRepository.add(movie)).thenReturn(id);
		when(_mockRepository.update(updatedMovie)).thenReturn(updatedMovie);

		_movieResource.addMovie(movie);
		Response result = _movieResource.updateMovie(updatedMovie);

		assertNotNull(result);
		assertEquals(result.getEntity(), updatedMovie);
		assertEquals(Response.status(Response.Status.OK).build().getStatus(), result.getStatus());
	}

	@Test
	public void updateNullMovieTest() {
		Movie movie = null;
		Response result = _movieResource.updateMovie(movie);

		assertNotNull(result);
		assertEquals(Response.status(Response.Status.BAD_REQUEST).build().getStatus(), result.getStatus());
	}

	@Test
	public void deleteValidMovieTest() {
		Long id = 1L;
		Movie movie = new Movie(null, new MovieData("Title"));
		Movie deletedMovie = new Movie(id, new MovieData("Title"));

		when(_mockRepository.add(movie)).thenReturn(id);
		when(_mockRepository.delete(deletedMovie)).thenReturn(deletedMovie);
		when(_mockRepository.get(id)).thenReturn(deletedMovie);

		_movieResource.addMovie(movie);
		Response result = _movieResource.deleteMovie(id);

		assertNotNull(result);
		assertEquals(result.getEntity(), deletedMovie);
		assertEquals(Response.status(Response.Status.OK).build().getStatus(), result.getStatus());
	}

	@Test
	public void deleteNullMovieTest() {
		Long id = null;
		Response result = _movieResource.deleteMovie(id);

		assertNotNull(result);
		assertEquals(Response.status(Response.Status.BAD_REQUEST).build().getStatus(), result.getStatus());
	}
}