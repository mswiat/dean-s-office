package com.validator;

import org.springframework.stereotype.Component;

@Component
public class NameValidator {

    public boolean validateFirstName(String firstName) {
        return firstName.matches("[A-Z][a-z]*");
    }

    public boolean validateLastName(String lastName) {
        return lastName.matches("^[A-Z][a-zA-Z]{3,}(?: [A-Z][a-zA-Z]*){0,2}$");
    }
}

//^ - start of string
//        [a-zA-Z]{4,} - 4 or more ASCII letters
//        (?: [a-zA-Z]+){0,2} - 0 to 2 occurrences of a space followed with one or more ASCII letters
//        $ - end of string.
//        If you need to restrict the words to start with Uppercase letters, you can use
//
//        ^[A-Z][a-zA-Z]{3,}(?: [A-Z][a-zA-Z]*){0,2}$
