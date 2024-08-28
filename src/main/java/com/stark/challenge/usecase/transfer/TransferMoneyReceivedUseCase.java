package com.stark.challenge.usecase.transfer;

import com.stark.challenge.domain.*;
import com.stark.challenge.infrastructure.Transfers;
import com.stark.challenge.usecase.invoice.InvoiceExecutor;
import com.starkbank.Invoice;
import org.springframework.stereotype.Service;

@Service
public class TransferMoneyReceivedUseCase implements InvoiceExecutor<Invoice> {

    private final Transfers transfer;

    public TransferMoneyReceivedUseCase(Transfers transfer) {
        this.transfer = transfer;
    }

    @Override
    public void execute(Invoice invoicePaid) {

        CurrencyAmount amountFinal = new CurrencyAmount(invoicePaid.amount.intValue()).minus(new CurrencyAmount(invoicePaid.fee));

        Payment payment = new Payment(
                new NationalRegistration("20018183000180"),
                "Stark Bank S.A.",
                amountFinal,
                new AccountInformation(
                        "20018183",
                        "0001",
                        "6341320293482496",
                        "payment"

                )
        );
        transfer.execute(payment);
    }
}
