package com.stark.challenge.infrastructure;


import com.stark.challenge.domain.InvoiceReceipt;
import com.starkbank.Invoice;

import java.util.List;

public interface InvoiceProvider {

    Invoice create(InvoiceReceipt invoiceReceipt);

    void create(List<InvoiceReceipt> invoiceReceipts);
}
