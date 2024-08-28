package com.stark.challenge.usecase;

import com.stark.challenge.domain.InvoiceReceipt;

import java.util.List;

public interface InvoiceUseCase {

    void create();

    void generate(List<InvoiceReceipt> invoiceReceipt);

}
