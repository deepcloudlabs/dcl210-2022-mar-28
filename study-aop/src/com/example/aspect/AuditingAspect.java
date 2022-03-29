package com.example.aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

public class AuditingAspect implements InvocationHandler {

	private final Object target;
	
	public AuditingAspect(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("%s is called at %s.".formatted(method.getName(),new Date()));
		var result = method.invoke(target, args);
		System.out.println("%s return %s.".formatted(method.getName(),result));
		return result;
	}

}
