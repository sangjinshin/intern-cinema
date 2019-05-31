package com.sailpoint.interncinema.service;

import java.util.List;

public interface Repository<T> {

	List<T> getAll();

	T get(Long id);

	Long add(T item);

	T update(T item);

	T delete(T item);
}
