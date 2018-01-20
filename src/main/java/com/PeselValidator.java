package com;

public class PeselValidator {


    public boolean isValid(Object value) {
        if (value == null) {
            return true;
        }

        String pesel = null;

        if (value instanceof String) {
            pesel = (String) value;
            pesel = removeNonDigit(pesel);
        } else if (value instanceof Long) {
            Long longValue = (Long) value;
            pesel = longValue.toString();
        } else
            return false;

        return isValidPESEL(pesel);

    }

    public static boolean isValidPESEL(String pesel) {
        int psize = pesel.length();
        if (psize != 11) {
            return false;
        }
        int[] weights = { 1, 3, 7, 9, 1, 3, 7, 9, 1, 3 };
        int j = 0, sum = 0, control = 0;
        int csum = Integer.valueOf(pesel.substring(psize - 1));
        for (int i = 0; i < psize - 1; i++) {
            char c = pesel.charAt(i);
            j = Integer.valueOf(String.valueOf(c));
            sum += j * weights[i];
        }
        control = 10 - (sum % 10);
        if (control == 10) {
            control = 0;
        }
        return (control == csum);

    }
    public static String removeNonDigit(String input) {
        return input.replaceAll("\\D*", "");
    }
}
