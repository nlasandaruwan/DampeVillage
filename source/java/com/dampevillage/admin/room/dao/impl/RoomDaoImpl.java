package com.dampevillage.admin.room.dao.impl;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;

import com.dampevillage.admin.room.dao.RoomDao;
import com.dampevillage.common.dao.impl.BaseDaoImpl;
import com.dampevillage.domain.Room;
import com.dampevillage.domain.RoomCategory;
import com.dampevillage.domain.RoomType;

public class RoomDaoImpl extends BaseDaoImpl<Room, Integer> implements RoomDao {

	public RoomDaoImpl() {
		super(Room.class);
	}

	@Override
	public void saveOrUpdateRoom(Room room) {

		RoomCategory roomCategoryLoaded = (RoomCategory) getHibernateTemplate()
				.load(RoomCategory.class, room.getRoomCategory().getId());

		RoomType roomTypeLoaded = (RoomType) getHibernateTemplate().load(
				RoomType.class, room.getRoomType().getId());

		room.setRoomCategory(roomCategoryLoaded);
		room.setRoomType(roomTypeLoaded);

		getHibernateTemplate().saveOrUpdate(room);
	}

	@Override
	public List<Room> getAllRooms() {

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Room.class);
		detachedCriteria.setFetchMode("roomCategory", FetchMode.JOIN);
		detachedCriteria.setFetchMode("roomType", FetchMode.JOIN);

		List<Room> roomLoaded = (List<Room>) getHibernateTemplate()
				.findByCriteria(detachedCriteria);

		for (Room room : roomLoaded) {

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
		return roomLoaded;
	}

	@Override
	public Room getRoomById(Integer id) {
		return getEntityById(id);
	}

	@Override
	public void deleteRoom(Room room) {
		getHibernateTemplate().delete(room);
	}
}
