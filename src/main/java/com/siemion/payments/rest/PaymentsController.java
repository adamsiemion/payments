package com.siemion.payments.rest;

import com.siemion.payments.domain.model.Payment;
import com.siemion.payments.domain.service.PaymentService;
import com.siemion.payments.rest.response.PaymentGetResponse;
import com.siemion.payments.rest.response.PaymentListResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/v1/payments")
@AllArgsConstructor
class PaymentsController {

    private final PaymentService paymentService;

    @GetMapping
    PaymentListResponse list(HttpServletRequest request) {
        return new PaymentListResponse(paymentService.findAll(),
                generateLinks(request));
    }

    @GetMapping("/{id}")
    PaymentGetResponse get(@PathVariable String id,
            HttpServletRequest request) {
        return new PaymentGetResponse(paymentService.getById(id),
                generateLinks(request));
    }

    @PutMapping("/{id}")
    PaymentGetResponse update(@PathVariable String id,
            @RequestBody Payment payment, HttpServletRequest request) {
        var paymentWithId = payment.withId(id);
        var updatedPayment = paymentService.update(paymentWithId);
        return new PaymentGetResponse(updatedPayment, generateLinks(request));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        paymentService.delete(id);
    }

    @PostMapping
    ResponseEntity<PaymentGetResponse> create(@RequestBody Payment payment,
            HttpServletRequest request) {
        var createdPayment = paymentService.create(payment);
        var uri = UriComponentsBuilder.fromUriString(getRequestUrl(request))
                .pathSegment(createdPayment.getId())
                .build()
                .toUri();
        return ResponseEntity.created(uri).body(
                new PaymentGetResponse(createdPayment, generateLinks(request)));
    }

    private Map<String, String> generateLinks(HttpServletRequest request) {
        return Collections.singletonMap("self", getRequestUrl(request));
    }

    private String getRequestUrl(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }
}
