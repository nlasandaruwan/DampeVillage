package com.dampevillage.payment.delegate;

import com.dampevillage.domain.Payment;
import com.dampevillage.payment.dao.PaymentDao;

public class PaymentDelagate {

	PaymentDao paymentDao;

	public void deletePayment(Payment payment) {
		paymentDao.deletePayment(payment);
	}

	/**
	 * @return the paymentDao
	 */
	public PaymentDao getPaymentDao() {
		return paymentDao;
	}

	/**
	 * @param paymentDao
	 *            the paymentDao to set
	 */
	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

}
