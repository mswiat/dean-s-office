package com.pg.engine.console.command.validator;

import org.springframework.stereotype.Component;

@Component
public class NumberInputDataValidator {
    private static final int INVALID_NUMBER = 0;

    public String removeNonDigit(String input) {
        return input.replaceAll("\\D*", "");
    }

    public int validateAndGetNumber(String input) {
        int number;
        if ("".equals(input)) {
            number = INVALID_NUMBER;
        } else {
            number = Integer.valueOf(input);
        }
        return number;
    }
}
