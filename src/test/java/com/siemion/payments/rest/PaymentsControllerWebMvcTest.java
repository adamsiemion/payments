package com.siemion.payments.rest;

import com.siemion.payments.domain.model.Payment;
import com.siemion.payments.domain.model.TestPayment;
import com.siemion.payments.domain.service.PaymentService;
import com.siemion.payments.rest.configuration.JacksonConfiguration;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@WebMvcTest
@Import(JacksonConfiguration.class)
public class PaymentsControllerWebMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PaymentService service;

    @Test
    public void shouldCorrectlyDeserializeFromJson() throws Exception {
        var requestBody = readFromClassPath("request-create-payment.json");
        var request = post("/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        var captor = ArgumentCaptor.forClass(Payment.class);
        given(service.create(captor.capture())).will(returnsFirstArg());
        // when
        mvc.perform(request);
        // then
        assertThat(captor.getValue()).isEqualTo(TestPayment.newPayment());
    }

    @Test
    public void shouldCorrectlySerializeToJson() throws Exception {
        var response = readFromClassPath("response-get-payment-sample.json");
        given(service.getById("id")).willReturn(TestPayment.newPayment("1", 0L));
        // when
        var result = mvc.perform(get("/v1/payments/id"));
        // then
        JSONAssert.assertEquals(response, result.andReturn().getResponse().getContentAsString(), false);
    }

    private String readFromClassPath(String fileName) throws IOException {
        return IOUtils.toString(this.getClass().getResourceAsStream("/" + fileName), Charset.defaultCharset());
    }
}