package com.stark.challenge.domain;

public record Payment(NationalRegistration nationalRegistration, String name, CurrencyAmount currencyAmount,
                      AccountInformation account) {
    public Payment {
        if (currencyAmount.amount() <= 0) {
            throw new IllegalArgumentException("The payment not be Zero");
        }
    }
}
