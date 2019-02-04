package com.siemion.payments.rest.response;


import com.siemion.payments.domain.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class PaymentListResponse {
    List<Payment> data;
    Map<String, String> links;
}
