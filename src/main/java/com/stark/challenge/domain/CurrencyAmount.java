package com.stark.challenge.domain;

import java.util.Currency;

public record CurrencyAmount(int amount, Currency currency) {
    public CurrencyAmount(int amount) {
        this(amount, Currency.getInstance("BRL"));
    }

    public CurrencyAmount minus(CurrencyAmount value) {

        if (!this.currency.equals(value.currency)) {
            throw new IllegalArgumentException("Currency is not same Locale");
        }

        int result = this.amount - value.amount;

        if (result < 0) {
            throw new ArithmeticException("The result of the calculation cannot be negative.");
        }

        return new CurrencyAmount(result);
    }

}
