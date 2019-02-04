package com.siemion.payments.data;

import com.siemion.payments.domain.model.Payment;
import com.siemion.payments.domain.service.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
class PaymentRepositorySpringData implements PaymentRepository {
    private final PaymentMongoRepository repository;

    @Override
    public Payment insert(@NonNull Payment payment) {
        return repository.insert(payment);
    }

    @Override
    public Optional<Payment> update(@NonNull Payment payment) {
        try {
            return Optional.of(repository.save(payment));
        } catch (OptimisticLockingFailureException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(@NonNull String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Payment> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Payment> findById(@NonNull String id) {
        return repository.findById(id);
    }
}

