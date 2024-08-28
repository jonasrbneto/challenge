package com.stark.challenge.entrypoint.http;

import com.stark.challenge.domain.CurrencyAmount;
import com.stark.challenge.domain.InvoiceReceipt;
import com.stark.challenge.domain.NationalRegistration;
import com.stark.challenge.entrypoint.http.invoice.InvoiceRequest;
import com.stark.challenge.usecase.InvoiceUseCase;
import com.starkbank.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class InvoiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);

    private final InvoiceUseCase invoiceCreateUseCase;

    public InvoiceController(InvoiceUseCase invoiceCreateUseCase) {
        this.invoiceCreateUseCase = invoiceCreateUseCase;
    }

    @PostMapping("/v1")
    public ResponseEntity create(@RequestBody InvoiceRequest invoiceRequest) {

        LOGGER.info("m=create request={}", invoiceRequest);

        var invoice = new InvoiceReceipt(
                new NationalRegistration(invoiceRequest.nationalRegistration()),
                new CurrencyAmount(invoiceRequest.amount()),
                invoiceRequest.name()
        );

        Invoice result = invoiceCreateUseCase.create(invoice);
        return ResponseEntity.ok(result);
    }


}
