package com.dampevillage.admin.room.dao;

import java.util.List;

import com.dampevillage.domain.Room;

public interface RoomDao {

	void saveOrUpdateRoom(Room roomCategory);

	List<Room> getAllRooms();

	Room getRoomById(Integer id);

	void deleteRoom(Room room);
}
