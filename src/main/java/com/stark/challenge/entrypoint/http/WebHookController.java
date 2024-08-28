package com.stark.challenge.entrypoint.http;

import com.stark.challenge.usecase.invoice.InvoiceExecutor;
import com.stark.challenge.usecase.invoice.InvoiceStrategy;
import com.stark.challenge.usecase.invoice.Type;
import com.starkbank.Event;
import com.starkbank.Invoice;
import com.starkbank.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class WebHookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebHookController.class);

    private final InvoiceStrategy invoiceStrategy;

    public WebHookController(InvoiceStrategy invoiceStrategy) {
        this.invoiceStrategy = invoiceStrategy;
    }

    @PostMapping("/v2/webhook")
    public ResponseEntity webhook(@RequestBody String webhookRequest, @RequestHeader("digital-signature") String digitalSignature) throws Exception {

        LOGGER.info("m=webhook request={}", webhookRequest.toString());

        Event event = Event.parse(webhookRequest, digitalSignature, Settings.user);
        switch (event.subscription) {
            case "invoice": {
                Invoice.Log log = ((Event.InvoiceEvent) event).log;
                InvoiceExecutor process = invoiceStrategy.process(Type.fromString(log.type));
                process.execute(log.invoice);
            }
        }

        return ResponseEntity.ok(event);
    }

}
