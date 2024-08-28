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

import java.util.Optional;

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
                Type type = Type.fromString(log.type);
                Optional<InvoiceExecutor> process = invoiceStrategy.process(type);
                process.ifPresentOrElse(
                        executor -> executor.execute(log.invoice),
                        () -> LOGGER.info("m=webhook message=ignored_by_type type={}", type)
                );
            }
        }

        return ResponseEntity.ok(event);
    }

}
