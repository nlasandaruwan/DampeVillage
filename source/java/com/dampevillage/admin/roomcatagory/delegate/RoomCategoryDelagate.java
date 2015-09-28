package com.dampevillage.admin.roomcatagory.delegate;

import java.util.List;

import com.dampevillage.admin.roomcatagory.dao.RoomCategoryDao;
import com.dampevillage.domain.RoomCategory;

public class RoomCategoryDelagate {

	private RoomCategoryDao roomCategoryDao;

	public void saveOrUpdateRoomCategory(RoomCategory roomCategory) {
		
		roomCategory.setImageName(roomCategory.getRoomCategoryType()+".jpg");
		roomCategoryDao.saveOrUpdateRoomCategory(roomCategory);
	}

	public List<RoomCategory> getAllRoomCategory() {
		return roomCategoryDao.getAllRoomCategory();
	}

	public RoomCategory getRoomCategoryById(Integer id) {
		return roomCategoryDao.getRoomCategoryById(id);
	}

	public void deleteRoomCategory(RoomCategory roomCategory){
		roomCategoryDao.deleteRoomCategory(roomCategory);
	}
	/**
	 * @return the roomCategoryDao
	 */
	public RoomCategoryDao getRoomCategoryDao() {
		return roomCategoryDao;
	}

	/**
	 * @param roomCategoryDao
	 *            the roomCategoryDao to set
	 */
	public void setRoomCategoryDao(RoomCategoryDao roomCategoryDao) {
		this.roomCategoryDao = roomCategoryDao;
	}

}
