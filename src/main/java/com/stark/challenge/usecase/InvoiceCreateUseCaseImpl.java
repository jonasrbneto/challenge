package com.stark.challenge.usecase;

import com.stark.challenge.domain.InvoiceReceipt;
import com.stark.challenge.infrastructure.InvoiceProvider;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InvoiceCreateUseCaseImpl implements InvoiceUseCase {

    private final InvoiceProvider invoiceProvider;

    public InvoiceCreateUseCaseImpl(InvoiceProvider invoiceProvider) {
        this.invoiceProvider = invoiceProvider;
    }

    @Override
    public void create() {
        invoiceProvider.create();
    }

    @Override
    public void generate(List<InvoiceReceipt> invoiceReceipt) {
        invoiceProvider.create(invoiceReceipt);
    }

}
