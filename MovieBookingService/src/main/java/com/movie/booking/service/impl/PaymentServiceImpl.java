package com.movie.booking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.movie.booking.constants.CommonConstant;
import com.movie.booking.controller.BookingController;
import com.movie.booking.entity.Payment;
import com.movie.booking.exception.ResourceNotFoundException;
import com.movie.booking.model.PaymentModel;
import com.movie.booking.repository.PaymentRepository;
import com.movie.booking.service.BookingService;
import com.movie.booking.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private BookingService bookingService;

	@Override
	public Payment updatePayment(Payment payment, long paymentId) {
		Payment existingPayment = this.paymentRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException(CommonConstant.PAYMENT_NOT_FOUND_WITH_ID + paymentId));
		existingPayment.setTransactionNo(payment.getTransactionNo());
		existingPayment.setType(payment.getType());
		existingPayment.setAmount(payment.getAmount());
		existingPayment.setStatus(payment.getStatus());
		return this.paymentRepository.save(existingPayment);
	}

	@Override
	public PaymentModel generatePaymentStatus(Payment paymentIn) {
		Payment payment = getPaymentByTransactionNo(paymentIn.getTransactionNo());
		payment.setStatus(paymentIn.getStatus());
		payment = this.paymentRepository.save(payment);

		if (CommonConstant.PAYMENT_SUCCESS.equalsIgnoreCase(paymentIn.getStatus())) {
			long bookingId = bookingService.bookTicket(payment.getTransactionNo());

			PaymentModel paymentModel = new PaymentModel(payment.getId(), payment.getTransactionNo(), payment.getType(),
					payment.getAmount(), payment.getStatus());
			Link autoRedirectLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(BookingController.class).generateTicket(bookingId))
					.withRel("autoRedirect");
			return paymentModel.add(autoRedirectLink);
		}
		return null;
	}

	@Override
	public Payment getPaymentByTransactionNo(String transactionNo) {
		return paymentRepository.findByTransactionNo(transactionNo).orElseThrow(
				() -> new ResourceNotFoundException(CommonConstant.TRANSACTION_NOT_FOUND_WITH_ID + transactionNo));
	}

}
