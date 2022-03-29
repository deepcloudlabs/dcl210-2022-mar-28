package com.example.aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class CachingAspect implements InvocationHandler {
    private Map<String,Map<Integer,Object>> resultCache = new ConcurrentHashMap<>();
	private final Object target;
	
	public CachingAspect(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		var cache = resultCache.get(method.getName());
		var hashCode = Objects.hash(args);
		if (Objects.isNull(cache)) {
			cache = new ConcurrentHashMap<Integer,Object>();
			resultCache.put(method.getName(), cache);
		} else {
			var cachedResult = cache.get(hashCode);
			if (Objects.nonNull(cachedResult))
				return cachedResult;
		}
		var result = method.invoke(target, args);
		cache.put(hashCode, result);
		return result;
	}

}
