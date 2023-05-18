package com.gabriel.slot.service;

import com.gabriel.slot.domain.model.Spin;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("unitTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValidatorServiceTest {

    //Fields
    @Autowired
    private transient ValidatorService validatorService;

    @Test

    public void validateTest() {
        Spin spin = new Spin();
        spin.setNumLines(5);
        spin.setStake((short) 15);

        validatorService.validate(spin);

        spin.setStake((short) -15);
        Assertions.assertThrows(ValidationException.class, () -> {
            validatorService.validate(spin);
        });

        spin.setStake((short) 15);
        spin.setNumLines(-5);

        Assertions.assertThrows(ValidationException.class, () -> {
            validatorService.validate(spin);
        });

        spin.setNumLines(5);
        validatorService.validate(spin);
    }

}
