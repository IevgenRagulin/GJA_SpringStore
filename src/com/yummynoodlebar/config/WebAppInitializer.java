package com.yummynoodlebar.config;

import javax.servlet.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Order(1)
public class WebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("get root config classes");
		return new Class<?>[] { SecurityConfig.class, JPAConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("get servlet config classes");
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		System.out.println("get servlet mappings");
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		System.out.println("get servlet filters");
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		return new Filter[] { characterEncodingFilter };
	}
}
