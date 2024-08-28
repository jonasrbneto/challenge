package com.stark.challenge.usecase;

import com.stark.challenge.domain.InvoiceReceipt;
import com.starkbank.Invoice;

import java.util.List;

public interface InvoiceUseCase {

    Invoice create(InvoiceReceipt invoiceReceipt);

    void generate(List<InvoiceReceipt> invoiceReceipt);

}
