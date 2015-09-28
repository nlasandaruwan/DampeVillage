package com.dampevillage.admin.roomrate.dao;

import java.util.Date;
import java.util.List;

import com.dampevillage.domain.RoomRate;

public interface RoomRateDao {

	void saveOrUpdateRoomRate(RoomRate roomRate);

	List<RoomRate> getAllRoomRates();

	RoomRate getRoomRateById(Integer id);

	void deleteRoomRate(RoomRate roomRate);

	List<RoomRate> searchRoomRate(Date fromDate, Date toDate, int... other);

	boolean isRateDatesOverlap(Date fromDate, Date toDate, int... other);
}
