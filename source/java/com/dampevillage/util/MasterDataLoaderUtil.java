package com.dampevillage.util;

import java.util.List;

import com.dampevillage.domain.HotelProfile;
import com.dampevillage.domain.RoomCategory;
import com.dampevillage.domain.RoomType;
import com.dampevillage.manager.Manager;

public class MasterDataLoaderUtil {

	private static Manager manager;
	static {
		manager = (Manager) BeanUtil.getBean("hotelmanager");
	}

	public static List<RoomType> getAllRoomTypes() {
		return manager.getAllRoomType();
	}

	public static List<RoomCategory> getAllRoomCategories() {
		return manager.getAllRoomCategory();
	}
	
	public static HotelProfile getHotelProfile() {
		return manager.getHotelProfile();
	}
}
