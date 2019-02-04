package com.siemion.payments.domain.service;

public class UpdateConflictException extends RuntimeException {

    UpdateConflictException(String message) {
        super(message);
    }
}
