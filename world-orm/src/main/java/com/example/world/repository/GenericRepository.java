package com.example.world.repository;

import java.util.Collection;
import java.util.Optional;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface GenericRepository<E, K> {
	Optional<E> findOne(K key);

	Collection<E> findAll();

	E add(E entity);

	E update(E entity);

	Optional<E> remove(K key);
}
