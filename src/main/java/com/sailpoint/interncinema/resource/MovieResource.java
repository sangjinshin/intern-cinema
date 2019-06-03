package com.sailpoint.interncinema.resource;

import com.google.inject.Inject;
import com.sailpoint.interncinema.model.Movie;
import com.sailpoint.interncinema.service.Repository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("movies")
public class MovieResource {

	Repository<Movie> _repository;

	@Inject
	public MovieResource(Repository repository) {
		this._repository = repository;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> getAllMovies() {

		return _repository.getAll();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMovie(Movie movie) {
		if (movie == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		Long id = _repository.add(movie);

		Movie output = _repository.get(id);

		return Response.status(Response.Status.CREATED).entity(output).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateMovie(Movie movie) {
		if (movie == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		Movie output = _repository.update(movie);

		return Response.status(Response.Status.OK).entity(output).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMovie(@PathParam("id") Long id) {
		if (id == null)
			return Response.status(Response.Status.BAD_REQUEST).build();

		Movie output = _repository.delete(_repository.get(id));
		if (output == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		return Response.status(Response.Status.OK).entity(output).build();
	}
}
