package com.stark.challenge.infrastructure;

import com.starkbank.Invoice;
import com.stark.challenge.domain.InvoiceReceipt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.starkbank.Project;
import com.starkbank.Settings;
import org.springframework.stereotype.Service;

@Service
public class InvoiceStark implements InvoiceProvider {

    private final Project project;

    public InvoiceStark(Project project) {
        this.project = project;
    }

    @Override
    public Invoice create(InvoiceReceipt invoiceReceipt) {
        Invoice invoice = extracted(invoiceReceipt);
        try {
            return Invoice.create(List.of(invoice)).getFirst();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void create(List<InvoiceReceipt> invoiceReceipts) {
        List<Invoice> invoices = convert(invoiceReceipts);
        try {
            invoices = Invoice.create(invoices);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (Invoice invoice : invoices) {
            System.out.println(invoice);
        }

    }


    private static List<Invoice> convert(List<InvoiceReceipt> invoiceReceipts) {
        List<Invoice> invoices = new ArrayList<>();
        invoiceReceipts.stream().forEach(ir -> {
                    invoices.add(extracted(ir));
                }
        );
        return invoices;
    }

    private static Invoice extracted(InvoiceReceipt ir) {
        Map<String, Object> invoiceMap = Map.of(
                "amount", ir.amount().amount(),
                "taxId", ir.nationalRegistration().id(),
                "name", ir.name(),
                "tags", new String[]{"challenge", "receiver"});
        try {
            return new Invoice(invoiceMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
