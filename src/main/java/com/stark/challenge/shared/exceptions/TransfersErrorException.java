package com.stark.challenge.shared.exceptions;

import com.starkbank.error.ErrorElement;

import java.util.List;

public class TransfersErrorException extends RuntimeException {

    private List<ErrorElement> errors;

    public TransfersErrorException(List<ErrorElement> errors) {
        this.errors = errors;
    }

    public List<ErrorElement> getErrors() {
        return errors;
    }
}
