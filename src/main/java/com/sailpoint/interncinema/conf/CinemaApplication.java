package com.sailpoint.interncinema.conf;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.sailpoint.interncinema.resource.MovieResource;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("cinema")
public class CinemaApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public CinemaApplication(@Context ServletContext servletContext) {
	}

	@Override
	public Set<Class<?>> getClasses() {
//		classes.add(MovieResource.class);

		return classes;
	}

	@Override
	public Set<Object> getSingletons() {

		CorsFilter corsFilter = new CorsFilter();
		corsFilter.getAllowedOrigins().add("*");
		corsFilter.setAllowedMethods("OPTIONS, GET, POST, DELETE, PUT, PATCH");
		singletons.add(corsFilter);

		Injector injector = Guice.createInjector(new MovieRepositoryModule());
		MovieResource movieResource = injector.getInstance(MovieResource.class);
		singletons.add(movieResource);

		return singletons;
	}
}