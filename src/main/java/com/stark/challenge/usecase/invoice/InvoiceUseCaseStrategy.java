package com.stark.challenge.usecase.invoice;

import com.stark.challenge.usecase.transfer.TransferMoneyReceivedUseCase;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InvoiceUseCaseStrategy implements InvoiceStrategy {

    private TransferMoneyReceivedUseCase transferMoneyReceivedUseCase;

    public InvoiceUseCaseStrategy(TransferMoneyReceivedUseCase transferMoneyReceivedUseCase) {
        this.transferMoneyReceivedUseCase = transferMoneyReceivedUseCase;
    }

    @Override
    public Optional<InvoiceExecutor> process(Type type) {
        switch (type) {
            case Type.PAID:
                return Optional.of(transferMoneyReceivedUseCase);
        }
        return Optional.empty();
    }


}
