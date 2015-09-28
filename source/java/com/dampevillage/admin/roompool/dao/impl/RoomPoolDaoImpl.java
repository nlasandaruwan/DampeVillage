package com.dampevillage.admin.roompool.dao.impl;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;

import com.dampevillage.admin.roompool.dao.RoomPoolDao;
import com.dampevillage.common.dao.impl.BaseDaoImpl;
import com.dampevillage.domain.RoomCategory;
import com.dampevillage.domain.RoomPool;
import com.dampevillage.domain.RoomType;

public class RoomPoolDaoImpl extends BaseDaoImpl<RoomPool, Integer> implements
		RoomPoolDao {

	public RoomPoolDaoImpl() {
		super(RoomPool.class);
	}

	@Override
	public void saveOrUpdateRoomPool(RoomPool roomPool) {

		RoomCategory roomCategoryLoaded = (RoomCategory) getHibernateTemplate()
				.load(RoomCategory.class, roomPool.getRoomCategory().getId());

		RoomType roomTypeLoaded = (RoomType) getHibernateTemplate().load(
				RoomType.class, roomPool.getRoomType().getId());

		roomPool.setRoomCategory(roomCategoryLoaded);
		roomPool.setRoomType(roomTypeLoaded);

		getHibernateTemplate().saveOrUpdate(roomPool);
	}

	@Override
	public List<RoomPool> getAllRoomPools() {

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(RoomPool.class);
		detachedCriteria.setFetchMode("roomCategory", FetchMode.JOIN);
		detachedCriteria.setFetchMode("roomType", FetchMode.JOIN);

		List<RoomPool> roomPoolsLoaded = (List<RoomPool>) getHibernateTemplate()
				.findByCriteria(detachedCriteria);

		for (RoomPool room : roomPoolsLoaded) {

			RoomCategory roomCategoryToSet = new RoomCategory();
			roomCategoryToSet.setId(room.getRoomCategory().getId());
			roomCategoryToSet.setRoomCategoryType(room.getRoomCategory()
					.getRoomCategoryType());

			room.setRoomCategory(null);
			room.setRoomCategory(roomCategoryToSet);

			RoomType roomTypeToSet = new RoomType();
			roomTypeToSet.setId(room.getRoomType().getId());
			roomTypeToSet.setRoomType(room.getRoomType().getRoomType());
			room.setRoomType(null);
			room.setRoomType(roomTypeToSet);

		}
		return roomPoolsLoaded;
	}

	@Override
	public RoomPool getRoomPoolById(Integer id) {
		return getEntityById(id);
	}

	@Override
	public void deleteRoomPool(RoomPool roomPool) {
		getHibernateTemplate().delete(roomPool);
	}
}
