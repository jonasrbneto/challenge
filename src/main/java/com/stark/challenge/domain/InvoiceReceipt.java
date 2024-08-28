package com.stark.challenge.domain;


public record InvoiceReceipt(NationalRegistration nationalRegistration, CurrencyAmount amount, String name) {

}
