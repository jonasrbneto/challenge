package com.stark.challenge.domain;

import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.AcceptAnyValidator;
import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.Validator;
import org.springframework.util.StringUtils;

public record NationalRegistration(String id) {

    private static final int CPF_LENGTH = 11;
    private static final int CNPJ_LENGTH = 14;

    private static final CPFFormatter CPF_FORMATTER = new CPFFormatter();
    private static final CNPJFormatter CNPJ_FORMATTER = new CNPJFormatter();
    private static final CPFValidator CPF_VALIDATOR = new CPFValidator();
    private static final CNPJValidator CNPJ_VALIDATOR = new CNPJValidator();

    public NationalRegistration {
        id = validateDocument(id);
    }

    private static String validateDocument(String id) {

        if (!StringUtils.hasLength(id)) {
            throw new IllegalArgumentException("NationalRegistration.id is Empty");
        }

        String idUnformated = id.replaceAll("[^\\d]", "");

        String registration;

        if (CPF_VALIDATOR.isEligible(idUnformated)) {
            String cpfUnformat = CPF_FORMATTER.unformat(id);
            CPF_VALIDATOR.assertValid(cpfUnformat);
            registration = cpfUnformat;
        } else if (CNPJ_VALIDATOR.isEligible(idUnformated)) {
            String cnpjUnformat = CNPJ_FORMATTER.unformat(id);
            CNPJ_VALIDATOR.assertValid(cnpjUnformat);
            registration = cnpjUnformat;
        } else {
            throw new IllegalArgumentException("NationalRegistration.id is not valid");
        }

        return registration;

    }

}
