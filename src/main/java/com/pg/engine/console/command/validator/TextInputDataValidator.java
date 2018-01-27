package com.pg.engine.console.command.validator;

import org.springframework.stereotype.Component;

@Component
public class TextInputDataValidator {
    private static final String INVALID_NUMBER = "";

    public String removeAllDigit(String input) {
        return input.replaceAll("\\d","");
    }
}
