package com.manulsoftware.weddings.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class SpringContextProvider {
	private static ApplicationContext context = null;

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		context = ctx;
	}
}
