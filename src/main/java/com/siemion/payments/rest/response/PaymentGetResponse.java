package com.siemion.payments.rest.response;


import com.siemion.payments.domain.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class PaymentGetResponse {
    Payment data;
    Map<String, String> links;
}
