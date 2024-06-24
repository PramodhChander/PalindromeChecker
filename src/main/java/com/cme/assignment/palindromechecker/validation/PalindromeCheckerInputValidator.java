package com.cme.assignment.palindromechecker.validation;

import org.springframework.stereotype.Component;

@Component
public class PalindromeCheckerInputValidator extends AbstractRegexValidator {

    public PalindromeCheckerInputValidator() {
        regex = "^[^\\d\\s]+$";
    }

}
