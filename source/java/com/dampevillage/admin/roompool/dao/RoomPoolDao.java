package com.dampevillage.admin.roompool.dao;

import java.util.List;

import com.dampevillage.domain.RoomPool;

public interface RoomPoolDao {

	void saveOrUpdateRoomPool(RoomPool roomPool);

	List<RoomPool> getAllRoomPools();

	RoomPool getRoomPoolById(Integer id);

	void deleteRoomPool(RoomPool roomPool);
}
