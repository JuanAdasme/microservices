package cl.ttpeople.microservices.interceptors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AroundDatabase {
	
	@Around("execution(* cl.ttpeople.microservices.services.CourseServiceImpl.*(..))")
	public Object logDatabase(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Before "+pjp.getSignature().getName());
		Object retVal = pjp.proceed();
		System.out.println("After");
		return retVal;
	}
}
