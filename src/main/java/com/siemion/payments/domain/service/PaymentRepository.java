package com.siemion.payments.domain.service;

import com.siemion.payments.domain.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    Payment insert(Payment payment);
    Optional<Payment> update(Payment payment);
    void deleteById(String id);
    List<Payment> findAll();
    Optional<Payment> findById(String id);
}
