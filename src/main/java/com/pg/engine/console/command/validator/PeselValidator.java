package com.pg.engine.console.command.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeselValidator {

    @Autowired
    NumberInputDataValidator numberInputDataValidator;

    public boolean isValid(Object value) {
        if (value == null) {
            return true;
        }

        String pesel;

        if (value instanceof String) {
            pesel = (String) value;
            pesel = numberInputDataValidator.removeNonDigit(pesel);
        } else if (value instanceof Long) {
            Long longValue = (Long) value;
            pesel = longValue.toString();
        } else {
            return false;
        }

        return validatePesel(pesel);

    }

    public static boolean validatePesel(String pesel) {
        int peselSize = pesel.length();
        if (peselSize != 11) {
            return false;
        }
        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int j;
        int sum = 0;
        int control;
        int csum = Integer.parseInt(pesel.substring(peselSize - 1));
        for (int i = 0; i < peselSize - 1; i++) {
            char c = pesel.charAt(i);
            j = Integer.parseInt(String.valueOf(c));
            sum += j * weights[i];
        }
        control = 10 - (sum % 10);
        if (control == 10) {
            control = 0;
        }
        return control == csum;

    }

}
