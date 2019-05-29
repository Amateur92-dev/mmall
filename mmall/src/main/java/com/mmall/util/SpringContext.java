package com.mmall.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
@SuppressWarnings("unused")
public class SpringContext implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		SpringContext.applicationContext=applicationContext;
	}
	/**
	 * ªÒ»°bean
	 * @param name
	 * @return
	 * @throws BeansException
	 */
	private static Object getBean(String name)throws BeansException {
		return applicationContext.getBean(name);
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}
