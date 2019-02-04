package com.siemion.payments.domain.service;

import com.siemion.payments.domain.model.Payment;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public Payment create(@NonNull Payment payment) {
        log.debug("Creating payment [{}]", payment.toString());
        Payment createdPayment = paymentRepository.insert(payment);
        log.info("Created payment with id {}", createdPayment.getId());
        return createdPayment;
    }

    public Payment update(@NonNull Payment payment) {
        log.debug("Updating payment with id {} [{}]", payment.getId(), payment);
        Optional<Payment> updatedPayment = paymentRepository.update(payment);
        if (updatedPayment.isPresent()) {
            log.info("Updated payment with id {}", payment.getId());
        }
        return updatedPayment.orElseThrow(() -> new UpdateConflictException(
                String.format("Payment with id %s was not updated because "
                        + "the provided version %d is outdated",
                        payment.getId(), payment.getVersion())));
    }

    public void delete(@NonNull String id) {
        paymentRepository.deleteById(id);
        log.info("Deleted payment with id {}", id);
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment getById(@NonNull String id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Payment with id %s was not found", id)));
    }
}
