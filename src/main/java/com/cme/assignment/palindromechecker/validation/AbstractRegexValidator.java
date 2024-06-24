package com.cme.assignment.palindromechecker.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractRegexValidator extends AbstractValidator {
    protected String regex;

    @Override
    public boolean isValid(Object value) {
        if(value instanceof String valueStr) {
            Pattern pattern = Pattern.compile(this.regex);
            Matcher matcher = pattern.matcher(valueStr);

            return matcher.find();
        }
        return false;
    }
}
