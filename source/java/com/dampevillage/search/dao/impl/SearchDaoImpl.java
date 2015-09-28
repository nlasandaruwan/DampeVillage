package com.dampevillage.search.dao.impl;

import java.util.Date;
import java.util.List;

import com.dampevillage.common.dao.impl.BaseDaoImpl;
import com.dampevillage.domain.Room;
import com.dampevillage.search.dao.SearchDao;

public class SearchDaoImpl extends BaseDaoImpl<Room, Integer> implements SearchDao {

	public SearchDaoImpl() {
		super(Room.class);
	}

	@Override
	public List<Room> searchRoome(Date fromDate, Date toDate, int roomType, int roomCategory) {

		Object[] values = { fromDate, toDate, fromDate, toDate, fromDate, toDate, roomType, roomCategory };

		List<Room> roomList = getHibernateTemplate().findByNamedQuery("com.dampevillage.domain.Room.checkAvailability",
				values);

		return roomList;
	}

}
