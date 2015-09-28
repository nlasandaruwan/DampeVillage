package com.dampevillage.search.delegate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dampevillage.common.util.CommonUtil;
import com.dampevillage.domain.Room;
import com.dampevillage.search.dao.SearchDao;

public class SearchDelagate {

	private SearchDao searchDao;

	public List<String> searchRooms(Date fromDate, Date toDate, int roomType, int roomCategory) {

		// Get All rooms according to matching criteria
		List<Room> rooms = searchDao.searchRoome(fromDate, toDate, roomType, roomCategory);

		List<String> roomsByString = null;
		StringBuffer roomFormated = null;

		// Get the first room of the list if list not null and not empty.
		if (rooms != null && !rooms.isEmpty()) {
			Room sampleRoom = rooms.get(0);
			if (sampleRoom != null) {

				roomsByString = new ArrayList<String>();
				roomFormated = new StringBuffer();

				// Room Category [0]
				roomFormated.append(sampleRoom.getRoomCategory().getRoomCategoryType() + "-");

				// Room Type [1]
				roomFormated.append(sampleRoom.getRoomType().getRoomType() + "-");

				// Floor number [2]
				roomFormated.append(sampleRoom.getFloorNumber() + "-");

				// Room Number [3]
				roomFormated.append(sampleRoom.getRoomNumber() + "-");

				// No of rooms [4]
				roomFormated.append(rooms.size() + "-");

				// From Date [5]
				roomFormated.append(CommonUtil.dateToString(fromDate) + "-");

				// To Date [6]
				roomFormated.append(CommonUtil.dateToString(toDate) + "-");

				// Room Category Id [7]
				roomFormated.append(sampleRoom.getRoomCategory().getId() + "-");

				// Room Type Id [8]
				roomFormated.append(sampleRoom.getRoomType().getId() + "-");
				roomsByString.add(roomFormated.toString());
			}
		} else {
			// List null or empty so set an empty array list to avoid null
			// pointer exception in pro_user.jsp
			roomsByString = new ArrayList<String>();
		}
		return roomsByString;
	}

	/**
	 * @return the searchDao
	 */
	public SearchDao getSearchDao() {
		return searchDao;
	}

	/**
	 * @param searchDao
	 *            the searchDao to set
	 */
	public void setSearchDao(SearchDao searchDao) {
		this.searchDao = searchDao;
	}

}
