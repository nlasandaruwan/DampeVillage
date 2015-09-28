package com.dampevillage.admin.roomrate.delegate;

import java.util.Date;
import java.util.List;

import com.dampevillage.admin.roomrate.dao.RoomRateDao;
import com.dampevillage.domain.RoomRate;

public class RoomRateDelagate {

	private RoomRateDao roomRateDao;

	public void saveOrUpdateRoomRate(RoomRate roomRate) {
		roomRateDao.saveOrUpdateRoomRate(roomRate);
	}

	public List<RoomRate> getAllRoomRates() {
		return roomRateDao.getAllRoomRates();
	}

	public RoomRate getRoomRateById(Integer id) {
		return roomRateDao.getRoomRateById(id);
	}

	public void deleteRoomRate(RoomRate roomRate) {
		roomRateDao.deleteRoomRate(roomRate);
	}

	public List<RoomRate> searchRoomRate(Date fromDate, Date toDate,
			int... other) {
		return roomRateDao.searchRoomRate(fromDate, toDate, other);
	}

	public boolean isRateDatesOverlap(Date fromDate, Date toDate, int... other) {
		return roomRateDao.isRateDatesOverlap(fromDate, toDate, other);
	}

	/**
	 * @return the roomRateDao
	 */
	public RoomRateDao getRoomRateDao() {
		return roomRateDao;
	}

	/**
	 * @param roomRateDao
	 *            the roomRateDao to set
	 */
	public void setRoomRateDao(RoomRateDao roomRateDao) {
		this.roomRateDao = roomRateDao;
	}
}
