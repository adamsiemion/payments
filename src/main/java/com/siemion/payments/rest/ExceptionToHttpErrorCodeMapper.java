package com.siemion.payments.rest;

import com.siemion.payments.domain.service.NotFoundException;
import com.siemion.payments.domain.service.UpdateConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
class ExceptionToHttpErrorCodeMapper {
    @ExceptionHandler(UpdateConflictException.class)
    public ResponseEntity conflict(final UpdateConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFound(final NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
