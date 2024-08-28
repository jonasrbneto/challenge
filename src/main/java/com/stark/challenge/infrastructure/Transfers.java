package com.stark.challenge.infrastructure;

import com.stark.challenge.domain.Payment;

public interface Transfers {
    void execute(Payment payment);
}
