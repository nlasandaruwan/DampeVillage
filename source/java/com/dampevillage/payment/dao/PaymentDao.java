package com.dampevillage.payment.dao;

import com.dampevillage.domain.Payment;

public interface PaymentDao {

	Payment saveOrUpdatePayment(Payment payment);
	
	void deletePayment(Payment payment);
}
