package com.stark.challenge.infrastructure;

import com.stark.challenge.domain.Payment;
import com.stark.challenge.entrypoint.http.WebHookController;
import com.stark.challenge.shared.exceptions.TransfersErrorException;
import com.starkbank.Project;
import com.starkbank.Transfer;
import com.starkbank.error.InputErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class TransferStark implements Transfers {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferStark.class);

    private final Project project;

    public TransferStark(Project project) {
        this.project = project;
    }

    @Override
    public void execute(Payment payment) {

        HashMap<String, Object> data = new HashMap<>();
        data.put("amount", payment.currencyAmount().amount());
        data.put("bankCode", payment.account().bankCode());
        data.put("branchCode", payment.account().branchCode());
        data.put("accountNumber", payment.account().accountNumber());
        data.put("taxId", payment.nationalRegistration().id());
        data.put("name", payment.name());

        try {
            var transfers = Transfer.create(List.of(new Transfer(data)));

            for (Transfer transfer : transfers) {
                LOGGER.info("", transfer);
            }

        } catch (InputErrors e) {
            throw new TransfersErrorException(e.errors);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
