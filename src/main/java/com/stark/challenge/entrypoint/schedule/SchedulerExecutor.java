package com.stark.challenge.entrypoint.schedule;


import com.stark.challenge.domain.CurrencyAmount;
import com.stark.challenge.domain.InvoiceReceipt;
import com.stark.challenge.domain.NationalRegistration;
import com.stark.challenge.usecase.InvoiceUseCase;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Component
public class SchedulerExecutor {

    private static final Logger log = LoggerFactory.getLogger(SchedulerExecutor.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final InvoiceUseCase invoiceCreateUseCase;
    private final Faker faker;

    public SchedulerExecutor(InvoiceUseCase invoiceCreateUseCase, Faker faker) {
        this.invoiceCreateUseCase = invoiceCreateUseCase;
        this.faker = faker;
    }

//    @Scheduled(fixedRate = 3, timeUnit = TimeUnit.HOURS)
    public void execute() {

        int randomInt = random().ints(8, 12).findFirst().getAsInt();
        List<InvoiceReceipt> invoiceReceipts = new ArrayList<>();


        IntStream.rangeClosed(1, randomInt).forEach(i -> {
            invoiceReceipts.add(
                    new InvoiceReceipt(new NationalRegistration(faker.cpf().valid(false)),
                            new CurrencyAmount(random().ints(100, 10000).findFirst().getAsInt()),
//                                new BigDecimal(random().doubles(1, 100).findFirst().getAsDouble()).setScale(2, RoundingMode.HALF_EVEN),
                            faker.name().name())
            );
        });

//        randomInt.ifPresent(r -> {
//            for (int i = 1; i <= r; i++) {
//                invoiceReceipts.add(
//                        new InvoiceReceipt(new NationalRegistration(faker.cpf().valid(false)),
//                                new CurrencyAmount(random().ints(100, 10000).findFirst().getAsInt()),
////                                new BigDecimal(random().doubles(1, 100).findFirst().getAsDouble()).setScale(2, RoundingMode.HALF_EVEN),
//                                faker.name().name())
//                );
//            }
//        });

        log.info("total: {}", invoiceReceipts.size());
        invoiceCreateUseCase.generate(invoiceReceipts);
    }

    private static Random random() {
        return new Random();
    }
}
