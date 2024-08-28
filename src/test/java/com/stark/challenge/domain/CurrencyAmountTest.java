package com.stark.challenge.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Currency;
import java.util.Locale;

class CurrencyAmountTest {

    CurrencyAmount subject;

    @BeforeEach
    public void setup() {
        this.subject = new CurrencyAmount(10);
    }

    @Test
    void should_success_when_subject_minus_1_result_9() {
        CurrencyAmount valueSubtract = new CurrencyAmount(1);
        CurrencyAmount result = subject.minus(valueSubtract);
        Assertions.assertEquals(9, result.amount());
    }

    @Test
    void should_error_when_subject_minus_the_currency_is_different() {
        CurrencyAmount valueSubtract = new CurrencyAmount(1, Currency.getInstance(Locale.US));
        Assertions.assertThrows(IllegalArgumentException.class, () -> subject.minus(valueSubtract));
    }

    @Test
    void should_error_when_subject_minus_result_is_negative() {
        CurrencyAmount valueSubtract = new CurrencyAmount(11);
        Assertions.assertThrows(ArithmeticException.class, () -> subject.minus(valueSubtract));
    }

}