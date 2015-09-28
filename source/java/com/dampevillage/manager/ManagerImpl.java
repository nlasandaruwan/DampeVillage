package com.dampevillage.manager;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import com.dampevillage.admin.hotelprofile.delegate.HotelProfileDelagate;
import com.dampevillage.admin.room.delegate.RoomDelagate;
import com.dampevillage.admin.roomcatagory.delegate.RoomCategoryDelagate;
import com.dampevillage.admin.roompool.delegate.RoomPoolDelagate;
import com.dampevillage.admin.roomrate.delegate.RoomRateDelagate;
import com.dampevillage.admin.roomtype.delegate.RoomTypeDelagate;
import com.dampevillage.customer.delegate.CustomerDelagate;
import com.dampevillage.domain.Customer;
import com.dampevillage.domain.HotelProfile;
import com.dampevillage.domain.Payment;
import com.dampevillage.domain.Reservation;
import com.dampevillage.domain.Room;
import com.dampevillage.domain.RoomCategory;
import com.dampevillage.domain.RoomPool;
import com.dampevillage.domain.RoomRate;
import com.dampevillage.domain.RoomType;
import com.dampevillage.log.delegate.LoginDelagate;
import com.dampevillage.payment.delegate.PaymentDelagate;
import com.dampevillage.reservation.delegate.ReservationDelagate;
import com.dampevillage.search.delegate.SearchDelagate;

public class ManagerImpl implements Manager {

	private LoginDelagate loginDelagate;

	private SearchDelagate searchDelagate;

	private CustomerDelagate customerDelagate;

	private RoomCategoryDelagate roomCategoryDelagate;

	private RoomDelagate roomDelagate;

	private RoomRateDelagate roomRateDelagate;

	private HotelProfileDelagate hotelProfileDelagate;

	private RoomPoolDelagate roomPoolDelagate;

	private ReservationDelagate reservationDelagate;

	private PaymentDelagate paymentDelagate;

	private RoomTypeDelagate roomTypeDelagate;

	@Override
	public Customer isValidUser(Customer customer) {
		return loginDelagate.isValidUser(customer);
	}

	@Override
	public List<String> searchRooms(Date fromDate, Date toDate, int roomType, int roomCategory) {
		return searchDelagate.searchRooms(fromDate, toDate, roomType, roomCategory);
	}

	@Override
	public void saveOrUpdateUser(Customer customer) throws ClassNotFoundException, IOException, MessagingException {
		customerDelagate.saveOrUpdateUser(customer);
	}

	@Override
	public void recoverUser(Customer customer) throws ClassNotFoundException, IOException, MessagingException {
		customerDelagate.recoverUser(customer);
	}

	@Override
	public Customer getUserById(Integer id) {
		return customerDelagate.getCustomerById(id);
	}

	@Override
	public List<String> getAllCustomerEmails() {
		return customerDelagate.getAllCustomerEmails();
	}

	@Override
	public void saveOrUpdateRoomCategory(RoomCategory roomCategory) {
		roomCategoryDelagate.saveOrUpdateRoomCategory(roomCategory);

	}

	@Override
	public List<RoomCategory> getAllRoomCategory() {
		return roomCategoryDelagate.getAllRoomCategory();
	}

	@Override
	public RoomCategory getRoomCategoryById(Integer id) {
		return roomCategoryDelagate.getRoomCategoryById(id);
	}

	@Override
	public void deleteRoomCategory(RoomCategory roomCategory) {
		roomCategoryDelagate.deleteRoomCategory(roomCategory);

	}

	@Override
	public List<Room> getAllRooms() {
		return roomDelagate.getAllRooms();
	}

	@Override
	public void saveOrUpdateRoom(Room room) {
		roomDelagate.saveOrUpdateRoom(room);

	}

	@Override
	public void deleteRoom(Room room) {
		roomDelagate.deleteRoom(room);

	}

	@Override
	public Room getRoomById(Integer id) {
		return roomDelagate.getRoomById(id);

	}

	@Override
	public HotelProfile getProfile() {
		return hotelProfileDelagate.getProfile();
	}

	@Override
	public void updateProfile(HotelProfile hotelProfile) {
		hotelProfileDelagate.updateProfile(hotelProfile);
	}

	@Override
	public void deleteRoomRate(RoomRate roomRate) {
		roomRateDelagate.deleteRoomRate(roomRate);

	}

	@Override
	public List<RoomRate> getAllRoomRates() {
		return roomRateDelagate.getAllRoomRates();
	}

	@Override
	public RoomRate getRoomRateById(Integer id) {
		return roomRateDelagate.getRoomRateById(id);
	}

	@Override
	public void saveOrUpdateRoomRate(RoomRate roomRate) {
		roomRateDelagate.saveOrUpdateRoomRate(roomRate);
	}

	@Override
	public void deleteRoomPool(RoomPool roomPool) {

		roomPoolDelagate.deleteRoomPool(roomPool);
	}

	@Override
	public List<RoomPool> getAllRoomPools() {
		return roomPoolDelagate.getAllRoomPools();
	}

	@Override
	public RoomPool getRoomPoolById(Integer id) {
		return roomPoolDelagate.getRoomPoolById(id);
	}

	@Override
	public void saveOrUpdateRoomPool(RoomPool roomPool) {
		roomPoolDelagate.saveOrUpdateRoomPool(roomPool);

	}

	@Override
	public List<RoomRate> searchRoomRate(Date fromDate, Date toDate, int... other) {
		return roomRateDelagate.searchRoomRate(fromDate, toDate, other);
	}

	@Override
	public void saveReservation(Reservation reservation) throws MessagingException {
		reservationDelagate.saveOrUpdateReservation(reservation);

	}

	@Override
	public List<Reservation> getAllReservation(int userId) {
		return reservationDelagate.getAllReservation(userId);
	}

	@Override
	public void deleteReservation(Reservation reservation) throws MessagingException {
		reservationDelagate.deleteReservation(reservation);

	}

	@Override
	public List<Reservation> searchReservations(Date fromDate, Date toDate, String userName) {
		return reservationDelagate.searchReservations(fromDate, toDate, userName);
	}

	@Override
	public void confirmReservation(Reservation reservation) {
		reservationDelagate.confirmReservation(reservation);
	}

	@Override
	public List<Customer> getSpecificUserType(Integer privilageId) {
		return customerDelagate.getCustomersByPrivilageId(privilageId);
	}

	@Override
	public void deleteUser(Customer customer) {
		customerDelagate.deleteUser(customer);
	}

	@Override
	public Reservation getReservationById(int reservation) {
		return reservationDelagate.getReservationById(reservation);
	}

	@Override
	public void deletePayment(Payment payment) {
		paymentDelagate.deletePayment(payment);
	}

	@Override
	public void deleteRoomType(RoomType roomType) {
		roomTypeDelagate.deleteRoomType(roomType);
	}

	@Override
	public List<RoomType> getAllRoomType() {
		return roomTypeDelagate.getAllRoomTypes();
	}

	@Override
	public RoomType getRoomTypeById(Integer id) {
		return roomTypeDelagate.getRoomTypeById(id);
	}

	@Override
	public void saveOrUpdateRoomType(RoomType roomType) {
		roomTypeDelagate.saveOrUpdateRoomType(roomType);
	}

	@Override
	public Customer getUserToRecoverPassword(Customer customer) {
		return customerDelagate.getUserToRecoverPassword(customer);
	}

	@Override
	public boolean isRateDatesOverlap(Date fromDate, Date toDate, int... other) {
		return roomRateDelagate.isRateDatesOverlap(fromDate, toDate, other);
	}

	@Override
	public HotelProfile getHotelProfile() {
		return hotelProfileDelagate.getProfile();
	}

	/**
	 * @return the loginDelagate
	 */
	public LoginDelagate getLoginDelagate() {
		return loginDelagate;
	}

	/**
	 * @param loginDelagate
	 *            the loginDelagate to set
	 */
	public void setLoginDelagate(LoginDelagate loginDelagate) {
		this.loginDelagate = loginDelagate;
	}

	/**
	 * @return the searchDelagate
	 */
	public SearchDelagate getSearchDelagate() {
		return searchDelagate;
	}

	/**
	 * @param searchDelagate
	 *            the searchDelagate to set
	 */
	public void setSearchDelagate(SearchDelagate searchDelagate) {
		this.searchDelagate = searchDelagate;
	}

	/**
	 * @return the customerDelagate
	 */
	public CustomerDelagate getCustomerDelagate() {
		return customerDelagate;
	}

	/**
	 * @param customerDelagate
	 *            the customerDelagate to set
	 */
	public void setCustomerDelagate(CustomerDelagate customerDelagate) {
		this.customerDelagate = customerDelagate;
	}

	/**
	 * @return the roomCategoryDelagate
	 */
	public RoomCategoryDelagate getRoomCategoryDelagate() {
		return roomCategoryDelagate;
	}

	/**
	 * @param roomCategoryDelagate
	 *            the roomCategoryDelagate to set
	 */
	public void setRoomCategoryDelagate(RoomCategoryDelagate roomCategoryDelagate) {
		this.roomCategoryDelagate = roomCategoryDelagate;
	}

	/**
	 * @return the roomDelagate
	 */
	public RoomDelagate getRoomDelagate() {
		return roomDelagate;
	}

	/**
	 * @param roomDelagate
	 *            the roomDelagate to set
	 */
	public void setRoomDelagate(RoomDelagate roomDelagate) {
		this.roomDelagate = roomDelagate;
	}

	/**
	 * @return the hotelProfileDelagate
	 */
	public HotelProfileDelagate getHotelProfileDelagate() {
		return hotelProfileDelagate;
	}

	/**
	 * @param hotelProfileDelagate
	 *            the hotelProfileDelagate to set
	 */
	public void setHotelProfileDelagate(HotelProfileDelagate hotelProfileDelagate) {
		this.hotelProfileDelagate = hotelProfileDelagate;
	}

	/**
	 * @return the roomRateDelagate
	 */
	public RoomRateDelagate getRoomRateDelagate() {
		return roomRateDelagate;
	}

	/**
	 * @param roomRateDelagate
	 *            the roomRateDelagate to set
	 */
	public void setRoomRateDelagate(RoomRateDelagate roomRateDelagate) {
		this.roomRateDelagate = roomRateDelagate;
	}

	/**
	 * @return the roomPoolDelagate
	 */
	public RoomPoolDelagate getRoomPoolDelagate() {
		return roomPoolDelagate;
	}

	/**
	 * @param roomPoolDelagate
	 *            the roomPoolDelagate to set
	 */
	public void setRoomPoolDelagate(RoomPoolDelagate roomPoolDelagate) {
		this.roomPoolDelagate = roomPoolDelagate;
	}

	/**
	 * @return the reservationDelagate
	 */
	public ReservationDelagate getReservationDelagate() {
		return reservationDelagate;
	}

	/**
	 * @param reservationDelagate
	 *            the reservationDelagate to set
	 */
	public void setReservationDelagate(ReservationDelagate reservationDelagate) {
		this.reservationDelagate = reservationDelagate;
	}

	/**
	 * @return the paymentDelagate
	 */
	public PaymentDelagate getPaymentDelagate() {
		return paymentDelagate;
	}

	/**
	 * @param paymentDelagate
	 *            the paymentDelagate to set
	 */
	public void setPaymentDelagate(PaymentDelagate paymentDelagate) {
		this.paymentDelagate = paymentDelagate;
	}

	/**
	 * @return the roomTypeDelagate
	 */
	public RoomTypeDelagate getRoomTypeDelagate() {
		return roomTypeDelagate;
	}

	/**
	 * @param roomTypeDelagate
	 *            the roomTypeDelagate to set
	 */
	public void setRoomTypeDelagate(RoomTypeDelagate roomTypeDelagate) {
		this.roomTypeDelagate = roomTypeDelagate;
	}
}
