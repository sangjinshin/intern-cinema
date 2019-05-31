package com.sailpoint.interncinema.conf;

import com.google.inject.AbstractModule;
import com.sailpoint.interncinema.service.MovieRepository;
import com.sailpoint.interncinema.service.Repository;

public class MovieModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Repository.class).to(MovieRepository.class);
		//bind(Repository.class).toProvider(MovieRepositoryProvider.class);
	}
}