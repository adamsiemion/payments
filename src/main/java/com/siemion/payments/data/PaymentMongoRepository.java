package com.siemion.payments.data;

import com.siemion.payments.domain.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

interface PaymentMongoRepository extends MongoRepository<Payment, String> {
}
