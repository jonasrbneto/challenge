package com.stark.challenge.infrastructure;


import com.stark.challenge.domain.InvoiceReceipt;

import java.util.List;

public interface InvoiceProvider {

    void create();

    void create(List<InvoiceReceipt> invoiceReceipts);
}
