package com.siemion.payments.domain.service;

import com.siemion.payments.domain.model.Payment;
import com.siemion.payments.domain.model.TestPayment;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PaymentServiceTest {

    private static final String ID = "1";

    private PaymentRepository repository;
    private PaymentService service;
    private Payment payment = TestPayment.newPayment(ID, 0L);

    @Before
    public void initPaymentService() {
        repository = mock(PaymentRepository.class);
        service = new PaymentService(repository);
    }

    @Test
    public void update_shouldFail_whenRepositoryDidNotUpdate() {
        // given
        given(repository.update(payment)).willReturn(Optional.empty());
        // when
        ThrowingCallable callable = () -> service.update(payment);
        // then
        assertThatThrownBy(callable)
                .isInstanceOf(UpdateConflictException.class)
                .hasMessage("Payment with id 1 was not updated because the provided version 0 is outdated");
    }

    @Test
    public void update_shouldReturnUpdatedPayment_whenRepositoryDidUpdate() {
        // given
        var updatePayment = TestPayment.newPayment(ID, 0L);
        var updatedPayment = TestPayment.newPayment(ID, 1L);
        given(repository.update(updatePayment)).willReturn(Optional.of(updatedPayment));
        // when
        var actual = service.update(updatePayment);
        // then
        assertThat(actual).isEqualTo(updatedPayment);
    }

    @Test
    public void getById_shouldFail_whenRepositoryDidNotReturnPayment() {
        // given
        // when
        ThrowingCallable callable = () -> service.getById(ID);
        // then
        assertThatThrownBy(callable)
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Payment with id 1 was not found");
    }

    @Test
    public void getById_shouldReturnPayment_whenRepositoryReturnedPayment() {
        // given
        given(repository.findById(ID)).willReturn(Optional.of(payment));
        // when
        var actual = service.getById(ID);
        // then
        assertThat(actual).isEqualTo(payment);
    }

    @Test
    public void create_shouldReturnCreatedPayment() {
        // given
        Payment newPayment = TestPayment.newPayment(null, null);
        Payment createdPayment = TestPayment.newPayment(ID, 0L);
        given(repository.insert(newPayment)).willReturn(createdPayment);
        // when
        var actual = service.create(newPayment);
        // then
        assertThat(actual).isEqualTo(createdPayment);
    }

    @Test
    public void delete_shouldCallRepository() {
        // given
        // when
        service.delete(ID);
        // then
        verify(repository).deleteById(ID);
    }

    @Test
    public void findAll_shouldReturnPayments() {
        // given
        given(repository.findAll()).willReturn(List.of(payment));
        // when
        var actual = service.findAll();
        // then
        assertThat(actual).isEqualTo(List.of(payment));
    }
}