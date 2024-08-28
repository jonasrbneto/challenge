package com.stark.challenge.entrypoint.sqs;


import com.stark.challenge.domain.CurrencyAmount;
import com.stark.challenge.domain.InvoiceReceipt;
import com.stark.challenge.domain.NationalRegistration;
import com.stark.challenge.usecase.InvoiceUseCase;
import io.awspring.cloud.sqs.annotation.SqsListener;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.IntStream;

@Component
public class SchedulerExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerExecutor.class);

    private final InvoiceUseCase invoiceCreateUseCase;
    private final Faker faker;

    public SchedulerExecutor(InvoiceUseCase invoiceCreateUseCase, Faker faker) {
        this.invoiceCreateUseCase = invoiceCreateUseCase;
        this.faker = faker;
    }

    @SqsListener("invoice_create_queue")
    public void execute(String message) {

        int randomInt = random().ints(8, 12).findFirst().getAsInt();
        List<InvoiceReceipt> invoiceReceipts = new ArrayList<>();

        IntStream.rangeClosed(1, randomInt).forEach(i -> {
            invoiceReceipts.add(
                    new InvoiceReceipt(new NationalRegistration(faker.cpf().valid(false)),
                            new CurrencyAmount(random().ints(100, 10000).findFirst().getAsInt()),
                            faker.name().name())
            );
        });

        LOGGER.info("total: {}", invoiceReceipts.size());
        invoiceCreateUseCase.generate(invoiceReceipts);
    }

    private static Random random() {
        return new Random();
    }
}
