package com.spring.sample.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.spring.sample.aop.annotation.CheckAccess;
import com.spring.sample.exception.NotAllowedAccessException;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AspectRulesInterceptor {

	@Before("@annotation(checkAccess)")
	public void checkAccess(JoinPoint joinPoint, CheckAccess checkAccess) {
		
		boolean hasAccess = false;
		String accessCode = "";
		Object[] args = joinPoint.getArgs();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
		Method method = methodSignature.getMethod();
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		assert args.length == parameterAnnotations.length;
		for (int argIndex = 0; argIndex < args.length; argIndex++) {
			for (Annotation annotation : parameterAnnotations[argIndex]) {
				if (!(annotation instanceof RequestParam))
					continue;
				RequestParam pathVariable = (RequestParam) annotation;
				if (pathVariable.name().equalsIgnoreCase("access")) {
					accessCode = args[argIndex].toString();
				}
			}
		}
		
		if (Strings.isNotBlank(accessCode)) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
						.getRequest();
			
			String permissions = request.getAttribute("permissions").toString();
			if (permissions.equalsIgnoreCase("permissionsFromFilter")) {
				if (accessCode.equalsIgnoreCase("yes")) 
					hasAccess = true;
			}
		}
		
		if (!hasAccess) {
			throw new NotAllowedAccessException("No Access for accessCode " + accessCode);
		}
	}
}

