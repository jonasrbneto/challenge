package com.stark.challenge.entrypoint.http.invoice;

public record InvoiceRequest(String nationalRegistration, Integer amount, String name) {
}
