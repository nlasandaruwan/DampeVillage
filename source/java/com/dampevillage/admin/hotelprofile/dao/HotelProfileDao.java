package com.dampevillage.admin.hotelprofile.dao;

import com.dampevillage.domain.HotelProfile;

public interface HotelProfileDao {

	void updateProfile(HotelProfile hotelProfile);

	HotelProfile getProfile();

}
