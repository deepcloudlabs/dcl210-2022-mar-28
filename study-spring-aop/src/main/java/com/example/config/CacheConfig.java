package com.example.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
	@Bean
	public CacheManager cacheManager() {
		var cacheManager = new SimpleCacheManager();
		var addCache = new ConcurrentMapCache("add-cache");
		var calculatorCache = new ConcurrentMapCache("calculator");
		List<Cache> caches = new ArrayList<>();
		caches.add(addCache);
		caches.add(calculatorCache);
		cacheManager.setCaches(caches);
		return cacheManager;
	}
	
}
