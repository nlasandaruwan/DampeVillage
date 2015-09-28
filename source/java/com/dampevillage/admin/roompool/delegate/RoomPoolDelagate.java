package com.dampevillage.admin.roompool.delegate;

import java.util.List;

import com.dampevillage.admin.roompool.dao.RoomPoolDao;
import com.dampevillage.domain.RoomPool;

public class RoomPoolDelagate {

	private RoomPoolDao roomPoolDao;

	public void saveOrUpdateRoomPool(RoomPool roomPool) {
		roomPoolDao.saveOrUpdateRoomPool(roomPool);
	}

	public List<RoomPool> getAllRoomPools() {
		return roomPoolDao.getAllRoomPools();
	}

	public RoomPool getRoomPoolById(Integer id) {
		return roomPoolDao.getRoomPoolById(id);
	}

	public void deleteRoomPool(RoomPool roomPool) {
		roomPoolDao.deleteRoomPool(roomPool);
	}

	/**
	 * @return the roomPoolDao
	 */
	public RoomPoolDao getRoomPoolDao() {
		return roomPoolDao;
	}

	/**
	 * @param roomPoolDao
	 *            the roomPoolDao to set
	 */
	public void setRoomPoolDao(RoomPoolDao roomPoolDao) {
		this.roomPoolDao = roomPoolDao;
	}

}
