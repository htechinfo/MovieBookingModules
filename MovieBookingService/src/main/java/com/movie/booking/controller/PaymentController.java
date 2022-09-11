package com.movie.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.booking.entity.Payment;
import com.movie.booking.model.PaymentModel;
import com.movie.booking.repository.PaymentRepository;
import com.movie.booking.service.PaymentService;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@RequestMapping("/api/mb/payment")
public class PaymentController {


	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private PaymentService paymentService;

	@PostMapping("/status")
	public PaymentModel paymentStatus(@RequestBody Payment paymentIn) {
		return paymentService.generatePaymentStatus(paymentIn);
	}

	@GetMapping("/{txnId}")
	public Payment getPaymentById(@PathVariable (value = "txnId") String txnId) {
		return paymentService.getPaymentByTransactionNo(txnId);
	}

	@Hidden
	@PostMapping
	public Payment createPayment(@RequestBody Payment payment) {
		return this.paymentRepository.save(payment);
	}

	@Hidden
	@PutMapping("/{id}")
	public Payment updatePayment(@RequestBody Payment payment, @PathVariable ("id") long paymentId) {
		 return paymentService.updatePayment(payment, paymentId);
	}
}
