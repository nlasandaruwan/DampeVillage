package com.dampevillage.admin.roomcatagory.dao.impl;

import java.util.List;

import com.dampevillage.admin.roomcatagory.dao.RoomCategoryDao;
import com.dampevillage.common.dao.impl.BaseDaoImpl;
import com.dampevillage.domain.RoomCategory;

public class RoomCategoryDaoImpl extends BaseDaoImpl<RoomCategory, Integer>
		implements RoomCategoryDao {

	public RoomCategoryDaoImpl() {
		super(RoomCategory.class);
	}

	@Override
	public void saveOrUpdateRoomCategory(RoomCategory roomCategory) {

		getHibernateTemplate().saveOrUpdate(roomCategory);
	}

	@Override
	public List<RoomCategory> getAllRoomCategory() {

		return (List<RoomCategory>) getHibernateTemplate().loadAll(
				RoomCategory.class);
	}

	@Override
	public RoomCategory getRoomCategoryById(Integer id) {
		return getEntityById(id);
	}

	@Override
	public void deleteRoomCategory(RoomCategory roomCategory) {
		getHibernateTemplate().delete(roomCategory);
	}
}
