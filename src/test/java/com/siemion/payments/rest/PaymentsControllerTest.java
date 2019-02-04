package com.siemion.payments.rest;

import com.siemion.payments.domain.model.Payment;
import com.siemion.payments.domain.model.TestPayment;
import com.siemion.payments.domain.service.PaymentService;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PaymentsControllerTest {

    private static final String API_URL = "http://localhost:8080/api";

    private PaymentService service;
    private PaymentsController controller;
    private HttpServletRequest request;

    @Before
    public void initPaymentsController() {
        service = mock(PaymentService.class);
        controller = new PaymentsController(service);
        request = mock(HttpServletRequest.class);
        given(request.getRequestURL()).willReturn(new StringBuffer(API_URL));
    }

    @Test
    public void create_shouldReturnLocationHeader() {
        // given
        var id = "1";
        var payment = TestPayment.newPayment(id, 0L);
        given(service.create(any())).willReturn(payment);
        // when
        var response = controller.create(payment, request);
        // then
        assertThat(response.getHeaders().getLocation().toString()).isEqualTo(API_URL + "/" + id);
    }

    @Test
    public void list_shouldReturnLinksWithSelf() {
        // given
        // when
        var response = controller.list(request);
        // then
        assertThat(response.getLinks()).isEqualTo(Map.of("self", API_URL));
    }

    @Test
    public void update_shouldCallServiceWithIdFromUrl_whenProvidedPaymentContainsDifferentId() {
        // given
        var id = "1";
        Payment paymentWithWrongId = TestPayment.newPayment("wrong id", 0L);
        Payment paymentWithIdFromUrl = TestPayment.newPayment(id, 0L);
        // when
        controller.update(id, paymentWithWrongId, request);
        // then
        verify(service).update(paymentWithIdFromUrl);
    }
}