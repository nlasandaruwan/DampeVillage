package com.dampevillage.domain;

import com.dampevillage.common.entity.BaseEntity;

public class Privilage extends BaseEntity {

	private String privilageName;
	private String description;

	/**
	 * @return the privilageName
	 */
	public String getPrivilageName() {
		return privilageName;
	}

	/**
	 * @param privilageName
	 *            the privilageName to set
	 */
	public void setPrivilageName(String privilageName) {
		this.privilageName = privilageName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
