package com.dampevillage.admin.hotelprofile.dao.impl;

import com.dampevillage.admin.hotelprofile.dao.HotelProfileDao;
import com.dampevillage.common.dao.impl.BaseDaoImpl;
import com.dampevillage.domain.HotelProfile;

public class HotelProfileDaoImpl extends BaseDaoImpl<HotelProfile, Integer>
		implements HotelProfileDao {

	public HotelProfileDaoImpl() {
		super(HotelProfile.class);
	}

	@Override
	public HotelProfile getProfile() {
		return (HotelProfile) getHibernateTemplate().get(HotelProfile.class, 1);

	}

	@Override
	public void updateProfile(HotelProfile hotelProfile) {
		getHibernateTemplate().saveOrUpdate(hotelProfile);
	}

}
