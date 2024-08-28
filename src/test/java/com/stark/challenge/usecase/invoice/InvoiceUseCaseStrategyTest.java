package com.stark.challenge.usecase.invoice;

import com.stark.challenge.usecase.transfer.TransferMoneyReceivedUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InvoiceUseCaseStrategyTest {

    private InvoiceUseCaseStrategy subject;

    @Mock
    private TransferMoneyReceivedUseCase transferMoneyReceivedUseCase;

    @BeforeEach
    public void setup() {
        subject = new InvoiceUseCaseStrategy(transferMoneyReceivedUseCase);
    }

    @Test
    void expect_executor_when_type_is_PAID() {
        Optional<InvoiceExecutor> process = subject.process(Type.PAID);
        assertTrue(process.isPresent());
    }

    @ParameterizedTest
    @EnumSource(value = Type.class, names = {"CREATED", "CREDITED", "CANCELED", "OVERDUE", "EXPIRED"})
    void expect_null_when_type_is_different_PAID(Type type) {
        Optional<InvoiceExecutor> process = subject.process(type);
        assertFalse(process.isPresent());
    }
}