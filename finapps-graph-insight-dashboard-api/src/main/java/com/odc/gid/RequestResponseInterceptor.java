package com.odc.gid;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import io.micrometer.core.instrument.MeterRegistry;

@Component
//@Order(1)
public class RequestResponseInterceptor implements HandlerInterceptor{
	
	public static AtomicInteger apiHitCount = new AtomicInteger(0);
	
	@Autowired
	  MeterRegistry registry;

	//@Override
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
		      Object handler, ModelAndView modelAndView) throws Exception {
		//apiHitCount.incrementAndGet();
		registry.counter("gid_api_hit_count").increment();
		registry.counter("gid_api_hit_count_"+String.valueOf(response.getStatus())).increment();
		//registry.counter("api_hit_count",String.valueOf(response.getStatus())).increment();
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
