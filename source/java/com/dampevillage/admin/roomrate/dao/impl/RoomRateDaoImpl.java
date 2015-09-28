package com.dampevillage.admin.roomrate.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;

import com.dampevillage.admin.roomrate.dao.RoomRateDao;
import com.dampevillage.common.dao.impl.BaseDaoImpl;
import com.dampevillage.domain.AccomodationMode;
import com.dampevillage.domain.RoomCategory;
import com.dampevillage.domain.RoomRate;
import com.dampevillage.domain.RoomType;

public class RoomRateDaoImpl extends BaseDaoImpl<RoomRate, Integer> implements
		RoomRateDao {

	public RoomRateDaoImpl() {
		super(RoomRate.class);
	}

	@Override
	public void saveOrUpdateRoomRate(RoomRate roomRate) {

		RoomCategory roomCategoryLoaded = (RoomCategory) getHibernateTemplate()
				.load(RoomCategory.class, roomRate.getRoomCategory().getId());

		RoomType roomTypeLoaded = (RoomType) getHibernateTemplate().load(
				RoomType.class, roomRate.getRoomType().getId());

		AccomodationMode accomodationModeLoaded = (AccomodationMode) getHibernateTemplate()
				.load(AccomodationMode.class,
						roomRate.getAccomodationMode().getId());

		roomRate.setRoomCategory(roomCategoryLoaded);
		roomRate.setRoomType(roomTypeLoaded);
		roomRate.setAccomodationMode(accomodationModeLoaded);

		getHibernateTemplate().saveOrUpdate(roomRate);
	}

	@Override
	public List<RoomRate> getAllRoomRates() {

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(RoomRate.class);
		detachedCriteria.setFetchMode("roomCategory", FetchMode.JOIN);
		detachedCriteria.setFetchMode("roomType", FetchMode.JOIN);
		detachedCriteria.setFetchMode("accomodationMode", FetchMode.JOIN);

		List<RoomRate> roomRateLoaded = (List<RoomRate>) getHibernateTemplate()
				.findByCriteria(detachedCriteria);

		for (RoomRate room : roomRateLoaded) {

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
		return roomRateLoaded;
	}

	@Override
	public List<RoomRate> searchRoomRate(Date fromDate, Date toDate,
			int... other) {
		Object[] values = { other[0], other[1], other[2], fromDate, toDate };

		List<RoomRate> roomRates = null;

		List<RoomRate> roomRateList = getHibernateTemplate().findByNamedQuery(
				"com.dampevillage.domain.RoomRate.searchRoomRate", values);

		// Both from date and to date are in one room rate definition. One room
		// rate applicable for all rooms in range

		if (roomRateList != null && !roomRateList.isEmpty()) {

			// get the date different between from and to dates.
			long dateDifferent = calculateDays(fromDate, toDate);

			RoomRate roomRate = roomRateList.get(0);

			// price per day
			long pricePerDay = roomRate.getPrice();

			// Calculate total price for all days plus current date.
			roomRate.setPrice(pricePerDay * (dateDifferent + 1));
			roomRates = roomRateList;
		} else {
			// From date and to date are in two different date ranges. Calculate
			// rate day basic.

			roomRates = new ArrayList<RoomRate>();

			// Create calendar objects from from and to dates.
			Calendar calendarFromDate = Calendar.getInstance();
			calendarFromDate.setTime(fromDate);

			Calendar calendarToDate = Calendar.getInstance();
			calendarToDate.setTime(toDate);

			int oneDay = 1;

			// Initially compare two dates
			int comparisionValue = calendarFromDate.compareTo(calendarToDate);

			// looping happen until from date less than or equal to to date.
			while (comparisionValue <= 0) {

				// Category
				values[0] = other[0];
				// Type
				values[1] = other[1];
				// Accommodation
				values[2] = other[2];
				// from date
				values[3] = calendarFromDate.getTime();
				values[4] = calendarFromDate.getTime();

				// Calculate room rate for day basic
				roomRateList = getHibernateTemplate().findByNamedQuery(
						"com.dampevillage.domain.RoomRate.searchRoomRate",
						values);

				if (roomRateList != null && !roomRateList.isEmpty()) {
					roomRates.add(roomRateList.get(0));
				}
				// Add one day for from date.
				calendarFromDate.add(Calendar.DAY_OF_MONTH, oneDay);
				// Calculate compare value
				comparisionValue = calendarFromDate.compareTo(calendarToDate);
			}

		}
		return roomRates;
	}

	@Override
	public boolean isRateDatesOverlap(Date fromDate, Date toDate, int... other) {
		Object[] values = { fromDate, toDate, other[0], other[1], other[2] };

		boolean isOverLap = false;

		List<RoomRate> roomRateList = getHibernateTemplate().findByNamedQuery(
				"com.dampevillage.domain.RoomRate.searchRoomRateOverLap",
				values);

		if (roomRateList != null && !roomRateList.isEmpty()) {

			// big date range difference.
			if (roomRateList.size() > 1) {
				isOverLap = true;
			} else {
				RoomRate roomRate = roomRateList.get(0);

				Calendar calendarFrom = Calendar.getInstance();
				calendarFrom.setTime(roomRate.getValidFrom());

				Calendar calendarTo = Calendar.getInstance();
				calendarTo.setTime(roomRate.getValidTo());

				Calendar calendarFromUi = Calendar.getInstance();
				calendarFromUi.setTime(fromDate);

				Calendar calendarToUi = Calendar.getInstance();
				calendarToUi.setTime(toDate);

				if (!(calendarFrom.get(Calendar.YEAR) == calendarFromUi
						.get(Calendar.YEAR))
						|| !(calendarFrom.get(Calendar.MONTH) == calendarFromUi
								.get(Calendar.MONTH))
						|| !(calendarFrom.get(Calendar.DAY_OF_MONTH) == calendarFromUi
								.get(Calendar.DAY_OF_MONTH))) {
					isOverLap = true;
				}
			}
		}
		return isOverLap;
	}

	// This method calculates different between given two days except current
	// date.
	private long calculateDays(Date dateEarly, Date dateLater) {
		return (dateLater.getTime() - dateEarly.getTime())
				/ (24 * 60 * 60 * 1000);
	}

	@Override
	public RoomRate getRoomRateById(Integer id) {
		return getEntityById(id);
	}

	@Override
	public void deleteRoomRate(RoomRate roomRate) {
		getHibernateTemplate().delete(roomRate);
	}

}
