package com.dampevillage.admin.roomtype.dao;

import java.util.List;

import com.dampevillage.domain.RoomType;

public interface RoomTypeDao {

	void saveOrUpdateRoomType(RoomType roomType);

	List<RoomType> getAllRoomTypes();

	RoomType getRoomTypeById(Integer id);

	void deleteRoomType(RoomType roomType);
}
