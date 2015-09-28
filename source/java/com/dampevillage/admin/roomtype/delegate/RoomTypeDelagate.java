package com.dampevillage.admin.roomtype.delegate;

import java.util.List;

import com.dampevillage.admin.roomtype.dao.RoomTypeDao;
import com.dampevillage.domain.RoomType;

public class RoomTypeDelagate {

	private RoomTypeDao roomTypeDao;

	public void saveOrUpdateRoomType(RoomType roomType) {
		roomTypeDao.saveOrUpdateRoomType(roomType);
	}

	public List<RoomType> getAllRoomTypes() {
		return roomTypeDao.getAllRoomTypes();
	}

	public RoomType getRoomTypeById(Integer id) {
		return roomTypeDao.getRoomTypeById(id);
	}

	public void deleteRoomType(RoomType roomType) {
		roomTypeDao.deleteRoomType(roomType);
	}

	/**
	 * @return the roomTypeDao
	 */
	public RoomTypeDao getRoomTypeDao() {
		return roomTypeDao;
	}

	/**
	 * @param roomTypeDao
	 *            the roomTypeDao to set
	 */
	public void setRoomTypeDao(RoomTypeDao roomTypeDao) {
		this.roomTypeDao = roomTypeDao;
	}
}
