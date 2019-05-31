package com.sailpoint.interncinema.conf;

import com.google.inject.Provider;
import com.sailpoint.interncinema.service.MovieRepository;
import com.sailpoint.interncinema.service.Repository;

public class MovieRepositoryProvider implements Provider<Repository> {

	@Override
	public Repository get() {
		Repository repository = new MovieRepository();
		return repository;
	}
}