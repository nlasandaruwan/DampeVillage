package com.dampevillage.admin.hotelprofile.delegate;

import com.dampevillage.admin.hotelprofile.dao.HotelProfileDao;
import com.dampevillage.domain.HotelProfile;

public class HotelProfileDelagate {

	private HotelProfileDao profileDao;

	public void updateProfile(HotelProfile hotelProfile) {
		profileDao.updateProfile(hotelProfile);
	}

	public HotelProfile getProfile() {
		return profileDao.getProfile();
	}

	/**
	 * @return the profileDao
	 */
	public HotelProfileDao getProfileDao() {
		return profileDao;
	}

	/**
	 * @param profileDao
	 *            the profileDao to set
	 */
	public void setProfileDao(HotelProfileDao profileDao) {
		this.profileDao = profileDao;
	}
}
