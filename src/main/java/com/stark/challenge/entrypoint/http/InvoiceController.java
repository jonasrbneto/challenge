package com.stark.challenge.entrypoint.http;

import com.stark.challenge.usecase.InvoiceUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InvoiceController {

    private final InvoiceUseCase invoiceCreateUseCase;

    public InvoiceController(InvoiceUseCase invoiceCreateUseCase) {
        this.invoiceCreateUseCase = invoiceCreateUseCase;
    }

    @PostMapping("v1")
    public ResponseEntity create() {
        invoiceCreateUseCase.create();
        return ResponseEntity.ok().build();
    }


}
