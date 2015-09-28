package com.dampevillage.reservation.dao;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import com.dampevillage.domain.Reservation;
import com.dampevillage.domain.Room;

public interface ReservationDao {

	void saveOrUpdateReservation(Reservation reservation);

	List<Reservation> getAllReservation(int userId);

	void deleteReservation(Reservation reservation)throws MessagingException;

	List<Reservation> searchReservations(Date fromDate, Date toDate, String userName);

	void confirmReservation(Reservation reservation);

	Reservation getReservationById(int reservation);

	List<Room> getRoomsOfReservation(int reservationId);
}
