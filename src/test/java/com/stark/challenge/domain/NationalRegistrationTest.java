package com.stark.challenge.domain;

import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.InvalidStateException;
import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NationalRegistrationTest {

    private Faker faker;
    private static final CPFFormatter CPF_FORMATTER = new CPFFormatter();
    private static final CNPJFormatter CNPJ_FORMATTER = new CNPJFormatter();

    @BeforeEach
    void setup() {
        faker = new Faker();
    }

    @Test
    public void should_success_when_cpf_is_valid() {
        String cpf = faker.cpf().valid(false);

        NationalRegistration nationalRegistration = Assertions.assertDoesNotThrow(() -> new NationalRegistration(cpf));
        Assertions.assertEquals(cpf, nationalRegistration.id());
    }

    @Test
    public void should_success_when_cpf_formated_is_valid() {
        String cpf = faker.cpf().valid(true);

        NationalRegistration nationalRegistration = Assertions.assertDoesNotThrow(() -> new NationalRegistration(cpf));
        Assertions.assertEquals(CPF_FORMATTER.unformat(cpf), nationalRegistration.id());
    }


    @Test
    public void should_success_when_cnpj_is_valid() {
        String cnpj = faker.cnpj().valid(false);

        NationalRegistration nationalRegistration = Assertions.assertDoesNotThrow(() -> new NationalRegistration(cnpj));
        Assertions.assertEquals(cnpj, nationalRegistration.id());
    }

    @Test
    public void should_success_when_cnpj_formated_is_valid() {
        String cnpj = faker.cnpj().valid(true);

        NationalRegistration nationalRegistration = Assertions.assertDoesNotThrow(() -> new NationalRegistration(cnpj));
        Assertions.assertEquals(CNPJ_FORMATTER.unformat(cnpj), nationalRegistration.id());
    }


    @Test
    public void should_error_when_cpf_is_invalid() {
        Assertions.assertThrows(InvalidStateException.class, () -> {
            new NationalRegistration(faker.cpf().invalid(false));
        });

    }

    @Test
    public void should_error_when_cnpj_is_invalid() {
        Assertions.assertThrows(InvalidStateException.class, () -> {
            new NationalRegistration(faker.cnpj().invalid(false));
        });
    }

    @Test
    public void should_error_when_id_is_invalid() {

        String id = "ERROR_ERROR";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new NationalRegistration(id);
        });
    }

    @Test
    public void should_error_when_id_is_empty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new NationalRegistration(null);
        });
    }

    @Test
    public void should_error_when_id_is_not_length_valid() {
        String id = "ERROR_ERROR_ERROR_ERROR";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new NationalRegistration(id);
        });
    }


}