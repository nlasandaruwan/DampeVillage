package com.dampevillage.util;

import java.util.ResourceBundle;

public class ResourceBundleUtil {

	ResourceBundle resourceBundle = null;

	public ResourceBundleUtil() {
		resourceBundle = ResourceBundle.getBundle("com.dampevillage.common.messageresource.ErrorMessages");
	}

	public String getLocaleSpecificValue(String key) {
		return resourceBundle.getString(key);
	}
}
