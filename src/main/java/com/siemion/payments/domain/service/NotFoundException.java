package com.siemion.payments.domain.service;

public class NotFoundException extends RuntimeException {

    NotFoundException(String message) {
        super(message);
    }
}
