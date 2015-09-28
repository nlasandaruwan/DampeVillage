package com.dampevillage.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanUtil {

	private static ApplicationContext applicationContext = null;

	private BeanUtil() {

	}

	public static Object getBean(String name) {
		initContex();
		return applicationContext.getBean(name);

	}

	private static void initContex() {

		if (null == applicationContext) {

			String prefix = "/WEB-INF/config";
			String[] paths = { prefix + "/beans-main.xml",
					prefix + "/Spring-bean-sessionfactory.xml" };
			applicationContext = new ClassPathXmlApplicationContext(paths);

		}
	}
}
