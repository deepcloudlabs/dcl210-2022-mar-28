package com.example.service;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGenerator {
	public static void populate(Object o) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException  {
		var clazz = o.getClass();
		for (var method : clazz.getDeclaredMethods()) {
			if (method.isAnnotationPresent(Random.class)) {
				var random = method.getAnnotation(Random.class);
				var numbers = ThreadLocalRandom.current()
						.ints(random.min(), random.max())
						.distinct()
						.limit(random.size())
						.sorted()
						.boxed()
						.toList();
				method.invoke(o,numbers);
			}
		}
	}
}
