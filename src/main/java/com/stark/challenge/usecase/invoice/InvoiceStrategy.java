package com.stark.challenge.usecase.invoice;

import java.util.Optional;

public interface InvoiceStrategy {

    Optional<InvoiceExecutor> process(Type type);
}
