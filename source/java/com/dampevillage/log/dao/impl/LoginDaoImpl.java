package com.dampevillage.log.dao.impl;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;

import com.dampevillage.common.dao.impl.BaseDaoImpl;
import com.dampevillage.domain.Customer;
import com.dampevillage.domain.Privilage;
import com.dampevillage.domain.Room;
import com.dampevillage.log.dao.LoginDao;

public class LoginDaoImpl extends BaseDaoImpl<Customer, Integer> implements LoginDao {

	private String customerQuery = "SELECT customer.last_name, customer.first_name, privilage.privilage_id"
			+ "FROM customer INNER JOIN privilage ON customer.privilage_id = privilage.privilage_id "
			+ "where customer.user_name = sandaruwan and customer.password = javaj2ee*";

	private String customerHQL = "from customer.lastName, customer.firstName, privilage.id"
			+ "FROM Customer as customer LEFT JOIN Privilage privilage ON customer.privilage = privilage.id "
			+ "where customer.userName = sandaruwan and customer.password = javaj2ee*";

	private String customerQuery2 = "select customer.firstName,customer.lasteName, customer.id, "
			+ "privilage.id  from Customer as customer, Privilage as privilage"
			+ " where customer.userName=? and customer.password=?" + "inner join customer using (privilage.id)";

	String string = "from Customer c where c.userName=? and c.password=?";

	public LoginDaoImpl() {
		super(Customer.class);
	}

	@Override
	public Customer isValidUser(Customer customerUi) {

		/*
		 * Object[] values = { customerUi.getUserName(),
		 * customerUi.getPassword()};
		 * 
		 * List<Customer> customers = getHibernateTemplate().findByNamedQuery(
		 * "com.dampevillage.domain.Customer.loginQuery", values);
		 */

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		detachedCriteria.add(Property.forName("userName").eq(customerUi.getUserName()));
		detachedCriteria.add(Property.forName("password").eq(customerUi.getPassword()));
		detachedCriteria.setFetchMode("privilage", FetchMode.JOIN);

		Customer customerLoaded = (Customer) getHibernateTemplate().findByCriteria(detachedCriteria).get(0);

		Customer customerReturn = null;
		Privilage privilage = null;

		if (customerLoaded != null) {
			customerReturn = new Customer();
			privilage = new Privilage();

			privilage.setId(customerLoaded.getPrivilage().getId());

			customerReturn.setFirstName(customerLoaded.getFirstName());
			customerReturn.setLasteName(customerLoaded.getLasteName());
			customerReturn.setId(customerLoaded.getId());
			customerReturn.setEmail(customerLoaded.getEmail());
			customerReturn.setPrivilage(privilage);

		}
		return customerReturn;
	}

}
