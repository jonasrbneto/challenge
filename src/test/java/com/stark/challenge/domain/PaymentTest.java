package com.stark.challenge.domain;

import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentTest {
    private Faker faker;

    @BeforeEach
    public void setup() {
        faker = new Faker();
    }

    @Test
    public void should_success_when_create_payment() {

        CurrencyAmount amount = new CurrencyAmount(1);
        String cnpj = faker.cnpj().valid(false);
        String fullName = faker.name().fullName();

        Payment payment = new Payment(
                new NationalRegistration(cnpj),
                fullName,
                amount,
                new AccountInformation(
                        "20018183",
                        "0001",
                        "6341320293482496",
                        "payment"

                )
        );

        Assertions.assertEquals(payment.currencyAmount().amount(), amount.amount());
        Assertions.assertEquals(payment.currencyAmount().currency(), amount.currency());
        Assertions.assertEquals(payment.name(), fullName);
        Assertions.assertEquals(payment.nationalRegistration().id(), cnpj);
        Assertions.assertEquals(payment.account().bankCode(), "20018183");
        Assertions.assertEquals(payment.account().branchCode(), "0001");
        Assertions.assertEquals(payment.account().accountNumber(), "6341320293482496");
        Assertions.assertEquals(payment.account().accountType(), "payment");
    }

    @Test
    public void should_error_when_create_payment_have_amount_minor_equal_0() {

        CurrencyAmount amount = new CurrencyAmount(0);
        String cnpj = faker.cnpj().valid(false);
        String fullName = faker.name().fullName();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Payment(
                    new NationalRegistration(cnpj),
                    fullName,
                    amount,
                    new AccountInformation(
                            "20018183",
                            "0001",
                            "6341320293482496",
                            "payment"

                    )
            );
        });
    }


}