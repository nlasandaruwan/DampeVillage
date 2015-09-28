package com.dampevillage.util;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

	Properties properties = null;

	public PropertyUtil() throws ClassNotFoundException, IOException {

		properties = new Properties();

		properties.load(Class.forName("com.dampevillage.util.PropertyUtil").getResourceAsStream(
				"/properties/EmailAddress.properties"));
		properties.load(Class.forName("com.dampevillage.util.PropertyUtil").getResourceAsStream(
				"/properties/ReportConfig.properties"));
		properties.load(Class.forName("com.dampevillage.util.PropertyUtil").getResourceAsStream(
				"/properties/EmailMessages.properties"));
	}

	public String getValue(String key) {
		return (String) properties.getProperty(key);
	}
}
