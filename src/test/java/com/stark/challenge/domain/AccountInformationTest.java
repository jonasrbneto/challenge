package com.stark.challenge.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountInformationTest {

    @Test
    void should_success_when_fill_all_fields() {
        AccountInformation account = new AccountInformation(
                "20018183",
                "0001",
                "6341320293482496",
                "payment"

        );
        Assertions.assertEquals(account.bankCode(), "20018183");
        Assertions.assertEquals(account.branchCode(), "0001");
        Assertions.assertEquals(account.accountNumber(), "6341320293482496");
        Assertions.assertEquals(account.accountType(), "payment");

    }

    @Test
    void should_error_when_bankCode_is_empty() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> {
            AccountInformation account = new AccountInformation(
                    null,
                    "0001",
                    "6341320293482496",
                    "payment"

            );

        });
        Assertions.assertEquals(error.getMessage(), "bankCode not be null");
    }

    @Test
    void should_error_when_branchCode_is_empty() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> {
            AccountInformation account = new AccountInformation(
                    "20018183",
                    null,
                    "6341320293482496",
                    "payment"

            );

        });
        Assertions.assertEquals(error.getMessage(), "branchCode not be null");
    }

    @Test
    void should_error_when_accountNumber_is_empty() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> {
            AccountInformation account = new AccountInformation(
                    "20018183",
                    "0001",
                    null,
                    "payment"

            );

        });
        Assertions.assertEquals(error.getMessage(), "accountNumber not be null");
    }

    @Test
    void should_error_when_accountType_is_empty() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> {
            AccountInformation account = new AccountInformation(
                    "20018183",
                    "0001",
                    "6341320293482496",
                    null

            );

        });
        Assertions.assertEquals(error.getMessage(), "accountType not be null");
    }
}