package com.dampevillage.reservation.dao.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.mail.MessagingException;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;

import com.dampevillage.common.dao.impl.BaseDaoImpl;
import com.dampevillage.domain.AccomodationMode;
import com.dampevillage.domain.Customer;
import com.dampevillage.domain.Reservation;
import com.dampevillage.domain.Room;
import com.dampevillage.reservation.dao.ReservationDao;
import com.dampevillage.util.emailnotification.EmailNotificationUtil;

public class ReservationDaoImpl extends BaseDaoImpl<Reservation, Integer> implements ReservationDao {

	public ReservationDaoImpl() {
		super(Reservation.class);
	}

	@Override
	public void saveOrUpdateReservation(Reservation reservation) {

		// getHibernateTemplate().getSessionFactory().getCurrentSession().evict(reservation);
		int custo = reservation.getCustomer().getId();
		Customer customerLoaded = (Customer) getHibernateTemplate().load(Customer.class, custo);

		int accomo = reservation.getAccomodationMode().getId();
		AccomodationMode accomodationModeLoaded = (AccomodationMode) getHibernateTemplate().load(
				AccomodationMode.class, accomo);

		reservation.setAccomodationMode(accomodationModeLoaded);
		reservation.setCustomer(customerLoaded);

		if (reservation.getId() > 0) {

			Reservation reservationLoaded = (Reservation) getHibernateTemplate().get(Reservation.class,
					reservation.getId());
			reservationLoaded.setRooms(new HashSet<Room>());
			getHibernateTemplate().update(reservationLoaded);
			
			getHibernateTemplate().merge(reservation);
		} else {
			getHibernateTemplate().saveOrUpdate(reservation);
		}

	}

	@Override
	public List<Reservation> getAllReservation(int userId) {

		Object[] values = { userId };
		List<Reservation> reservations = getHibernateTemplate().findByNamedQuery(
				"com.dampevillage.domain.Reservation.viewAllReservations", values);
		return reservations;
	}

	@Override
	public void deleteReservation(Reservation reservation) throws MessagingException {

		Reservation reservationLoaded = getEntityById(reservation.getId());
		reservationLoaded.setVersion(reservation.getVersion());
		getHibernateTemplate().delete(reservationLoaded);
		EmailNotificationUtil.notifyCancelReservation(reservationLoaded);
	}

	@Override
	public List<Reservation> searchReservations(Date fromDate, Date toDate, String userName) {
		Object[] values = { fromDate, toDate, userName };
		List<Reservation> reservations = getHibernateTemplate().findByNamedQuery(
				"com.dampevillage.domain.Reservation.searchReservations", values);
		return reservations;
	}

	@Override
	public void confirmReservation(Reservation reservation) {

		Reservation reservationLoaded = getEntityById(reservation.getId());
		reservationLoaded.setVersion(reservation.getVersion());
		reservationLoaded.setReservationStatus("CONFIRMED");
		getHibernateTemplate().saveOrUpdate(reservationLoaded);

	}

	@Override
	public Reservation getReservationById(int reservation) {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Reservation.class);
		detachedCriteria.add(Property.forName("id").eq(reservation));
		detachedCriteria.setFetchMode("payment", FetchMode.JOIN);

		Reservation reservationLoaded = (Reservation) getHibernateTemplate().findByCriteria(detachedCriteria).get(0);
		return reservationLoaded;
	}

	@Override
	public List<Room> getRoomsOfReservation(int reservationId) {
		Object[] values = { reservationId };
		List<Room> rooms = getHibernateTemplate().findByNamedQuery(
				"com.dampevillage.domain.Room.getRoomsOfReservation", values);
		return rooms;
	}
}
