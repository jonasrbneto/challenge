package com.stark.challenge.usecase.transfer;

import com.stark.challenge.infrastructure.Transfers;
import com.starkbank.Invoice;
import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Answers.CALLS_REAL_METHODS;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class TransferMoneyReceivedUseCaseTest {
    private TransferMoneyReceivedUseCase subject;
    private Faker faker;

    @Mock
    private Transfers transfers;

    @Mock(answer = CALLS_REAL_METHODS)
    private Invoice invoicePaid;

    @BeforeEach
    public void setup() {
        subject = new TransferMoneyReceivedUseCase(transfers);
        faker = new Faker();
    }

    @Test
    void should_success_when_pass_correct_values() {
        Mockito.doNothing().when(transfers).execute(Mockito.any());
        invoicePaid.amount = 4;
        invoicePaid.fee = 1;

        subject.execute(invoicePaid);

        Mockito.verify(transfers, Mockito.atMostOnce()).execute(Mockito.any());
    }

    @Test
    void should_error_when_fee_is_greater_than_amount() {
        invoicePaid.amount = 1;
        invoicePaid.fee = 5;
        ArithmeticException arithmeticException = assertThrows(ArithmeticException.class, () -> subject.execute(invoicePaid));

        Assertions.assertEquals(arithmeticException.getMessage(), "The result of the calculation cannot be negative.");
        Mockito.verify(transfers, never()).execute(Mockito.any());
    }

}