package com.dampevillage.reservation.delegate;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import com.dampevillage.domain.Reservation;
import com.dampevillage.domain.Room;
import com.dampevillage.reservation.dao.ReservationDao;
import com.dampevillage.search.dao.SearchDao;
import com.dampevillage.util.emailnotification.EmailNotificationUtil;

public class ReservationDelagate {

	private ReservationDao reservationDao;
	private SearchDao searchDao;

	public void saveOrUpdateReservation(Reservation reservation) throws MessagingException {

		// Load all remaining rooms.
		List<Room> rooms = searchDao.searchRoome(reservation.getFromDate(), reservation.getToDate(), reservation
				.getRoomTypeId(), reservation.getRoomCategoryId());

		// Load all room objects already allocated to this reservation
		// Applicable only in edit reservation.
		if (reservation.getId() > 0) {
			List<Room> roomsAlreadyAllocated = reservationDao.getRoomsOfReservation(reservation.getId());
			// Merge two room lists.
			rooms.addAll(roomsAlreadyAllocated);
			// reservationDao.deleteReservation(reservation);
		}

		for (int a = 0; a < reservation.getNoOfRooms(); a++) {

			reservation.addRoom(rooms.get(a));
		}

		reservationDao.saveOrUpdateReservation(reservation);
		EmailNotificationUtil.notifySaveOrUpdateReservation(reservation);
	}

	public List<Reservation> getAllReservation(int userId) {
		return reservationDao.getAllReservation(userId);
	}

	public void deleteReservation(Reservation reservation) throws MessagingException {
		reservationDao.deleteReservation(reservation);
		
	}

	public List<Reservation> searchReservations(Date fromDate, Date toDate, String userName) {
		return reservationDao.searchReservations(fromDate, toDate, userName);
	}

	public void confirmReservation(Reservation reservation) {
		reservationDao.confirmReservation(reservation);
	}

	public Reservation getReservationById(int reservation) {
		return reservationDao.getReservationById(reservation);
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

	/**
	 * @return the reservationDao
	 */
	public ReservationDao getReservationDao() {
		return reservationDao;
	}

	/**
	 * @param reservationDao
	 *            the reservationDao to set
	 */
	public void setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

}
