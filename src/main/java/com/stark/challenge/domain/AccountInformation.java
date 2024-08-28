package com.stark.challenge.domain;

import org.springframework.util.Assert;

public record AccountInformation(String bankCode, String branchCode, String accountNumber,
                                 String accountType) {
    public AccountInformation {

        Assert.hasLength(bankCode, "bankCode not be null");
        Assert.hasLength(branchCode, "branchCode not be null");
        Assert.hasLength(accountNumber, "accountNumber not be null");
        Assert.hasLength(accountType, "accountType not be null");

    }
}
