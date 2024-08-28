package com.stark.challenge.usecase.invoice;

public interface InvoiceStrategy {

    public InvoiceExecutor process(Type type);
}
