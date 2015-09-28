package com.dampevillage.admin.roomcatagory.dao;

import java.util.List;

import com.dampevillage.domain.RoomCategory;

public interface RoomCategoryDao {

	void saveOrUpdateRoomCategory(RoomCategory roomCategory);

	List<RoomCategory> getAllRoomCategory();
	
	RoomCategory getRoomCategoryById(Integer id);
	
	void deleteRoomCategory(RoomCategory roomCategory);
}
