package com.stark.challenge.domain;

public record InvoicePaidCalculate(CurrencyAmount amountReceived,
                                   CurrencyAmount fee) {

    public CurrencyAmount amountFinal() {
        CurrencyAmount result;
        try {
            result = amountReceived.minus(fee);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("fee not be greater than amount");
        }
        return result;
    }

}
