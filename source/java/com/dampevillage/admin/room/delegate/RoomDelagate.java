package com.dampevillage.admin.room.delegate;

import java.util.List;

import com.dampevillage.admin.room.dao.RoomDao;
import com.dampevillage.domain.Room;

public class RoomDelagate {

	private RoomDao roomDao;

	public void saveOrUpdateRoom(Room room) {
		roomDao.saveOrUpdateRoom(room);
	}

	public List<Room> getAllRooms() {
		return roomDao.getAllRooms();
	}

	public Room getRoomById(Integer id) {
		return roomDao.getRoomById(id);
	}

	public void deleteRoom(Room room) {
		roomDao.deleteRoom(room);
	}

	/**
	 * @return the roomDao
	 */
	public RoomDao getRoomDao() {
		return roomDao;
	}

	/**
	 * @param roomDao
	 *            the roomDao to set
	 */
	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}

}
