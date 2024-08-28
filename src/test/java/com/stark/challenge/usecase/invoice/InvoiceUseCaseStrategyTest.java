package com.stark.challenge.usecase.invoice;

import com.stark.challenge.usecase.transfer.TransferMoneyReceivedUseCase;
import com.starkbank.Invoice;
import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        InvoiceExecutor<Invoice> process = subject.process(Type.PAID);

        Assertions.assertEquals(TransferMoneyReceivedUseCase.class, process.getClass());
    }

    @Test
    void expect_null_when_type_is_different_PAID() {
        InvoiceExecutor<Invoice> process = subject.process(Type.CREATED);
        Assertions.assertNull(process);
    }
}