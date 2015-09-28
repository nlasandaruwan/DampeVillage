package com.dampevillage.admin.roomtype.dao.impl;

import java.util.List;

import com.dampevillage.admin.roomtype.dao.RoomTypeDao;
import com.dampevillage.common.dao.impl.BaseDaoImpl;
import com.dampevillage.domain.RoomType;

public class RoomTypeDaoImpl extends BaseDaoImpl<RoomType, Integer> implements RoomTypeDao {

	public RoomTypeDaoImpl() {
		super(RoomType.class);
	}

	@Override
	public void saveOrUpdateRoomType(RoomType roomType) {

		getHibernateTemplate().saveOrUpdate(roomType);
	}

	@Override
	public List<RoomType> getAllRoomTypes() {

		return (List<RoomType>) getHibernateTemplate().loadAll(RoomType.class);
	}

	@Override
	public RoomType getRoomTypeById(Integer id) {
		return getEntityById(id);
	}

	@Override
	public void deleteRoomType(RoomType roomType) {
		getHibernateTemplate().delete(roomType);
	}
}
