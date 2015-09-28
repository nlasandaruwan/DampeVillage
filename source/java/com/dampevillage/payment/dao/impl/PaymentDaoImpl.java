package com.dampevillage.payment.dao.impl;

import com.dampevillage.common.dao.impl.BaseDaoImpl;
import com.dampevillage.domain.Payment;
import com.dampevillage.payment.dao.PaymentDao;

public class PaymentDaoImpl extends BaseDaoImpl<Payment, Integer> implements PaymentDao {

	public PaymentDaoImpl() {
		super(Payment.class);
	}

	@Override
	public Payment saveOrUpdatePayment(Payment payment) {
		getHibernateTemplate().saveOrUpdate(payment);
		return payment;
	}

	@Override
	public void deletePayment(Payment payment) {
		getHibernateTemplate().delete(payment);
	}
}
