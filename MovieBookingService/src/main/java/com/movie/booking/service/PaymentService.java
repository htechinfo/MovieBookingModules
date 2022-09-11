package com.movie.booking.service;

import com.movie.booking.entity.Payment;
import com.movie.booking.model.PaymentModel;

public interface PaymentService {
	Payment updatePayment(Payment payment, long paymentId);
	
	PaymentModel generatePaymentStatus(Payment paymentIn);
	
	Payment getPaymentByTransactionNo(String transactionNo);
}
