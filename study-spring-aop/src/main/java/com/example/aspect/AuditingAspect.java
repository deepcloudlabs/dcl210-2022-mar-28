package com.example.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditingAspect {
	
	@Around("(@annotation(audited) || @target(audited)) && execution(* com.example..*(..))")
	public Object profile(ProceedingJoinPoint pjp,Audited audited) throws Throwable {
		System.out.println("%s is called at %s.".formatted(pjp.getSignature().getName(),new Date()));
		var result = pjp.proceed();
		System.out.println("%s return %s.".formatted(pjp.getSignature().getName(),result));
		return result;		
	}
}
