package com.dampevillage.search.dao;

import java.util.Date;
import java.util.List;

import com.dampevillage.domain.Room;

public interface SearchDao {

	List<Room> searchRoome(Date fromDate, Date toDate, int roomType, int roomCategory);
}
