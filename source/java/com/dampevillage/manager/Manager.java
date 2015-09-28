package com.dampevillage.manager;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import com.dampevillage.domain.Customer;
import com.dampevillage.domain.HotelProfile;
import com.dampevillage.domain.Payment;
import com.dampevillage.domain.Reservation;
import com.dampevillage.domain.Room;
import com.dampevillage.domain.RoomCategory;
import com.dampevillage.domain.RoomPool;
import com.dampevillage.domain.RoomRate;
import com.dampevillage.domain.RoomType;

public interface Manager {

	// User

	Customer getUserToRecoverPassword(Customer customer);

	Customer isValidUser(Customer customer);

	List<String> searchRooms(Date fromDate, Date toDate, int roomType, int roomCategory);

	void saveOrUpdateUser(Customer customer) throws ClassNotFoundException, IOException, MessagingException;

	void recoverUser(Customer customer) throws ClassNotFoundException, IOException, MessagingException;

	Customer getUserById(Integer id);

	List<Customer> getSpecificUserType(Integer privilageId);

	void deleteUser(Customer customer);

	List<String> getAllCustomerEmails();

	// Room category
	void saveOrUpdateRoomCategory(RoomCategory roomCategory);

	List<RoomCategory> getAllRoomCategory();

	RoomCategory getRoomCategoryById(Integer id);

	void deleteRoomCategory(RoomCategory roomCategory);

	// Room
	List<Room> getAllRooms();

	void saveOrUpdateRoom(Room room);

	Room getRoomById(Integer id);

	void deleteRoom(Room room);

	// Hotel Profile
	void updateProfile(HotelProfile hotelProfile);

	HotelProfile getProfile();

	// RoomRates
	List<RoomRate> getAllRoomRates();

	void saveOrUpdateRoomRate(RoomRate roomRate);

	RoomRate getRoomRateById(Integer id);

	List<RoomRate> searchRoomRate(Date fromDate, Date toDate, int... other);

	void deleteRoomRate(RoomRate roomRate);

	boolean isRateDatesOverlap(Date fromDate, Date toDate, int... other);

	// Room Pool
	void saveOrUpdateRoomPool(RoomPool roomPool);

	List<RoomPool> getAllRoomPools();

	RoomPool getRoomPoolById(Integer id);

	void deleteRoomPool(RoomPool roomPool);

	// Reservation
	void saveReservation(Reservation reservation) throws MessagingException;

	List<Reservation> getAllReservation(int userId);

	void deleteReservation(Reservation reservation)throws MessagingException;

	List<Reservation> searchReservations(Date fromDate, Date toDate, String userName);

	void confirmReservation(Reservation reservation);

	Reservation getReservationById(int reservation);

	// Payment

	void deletePayment(Payment payment);

	// Room Type

	void saveOrUpdateRoomType(RoomType roomType);

	List<RoomType> getAllRoomType();

	RoomType getRoomTypeById(Integer id);

	void deleteRoomType(RoomType roomType);

	// contact us

	HotelProfile getHotelProfile();

}
