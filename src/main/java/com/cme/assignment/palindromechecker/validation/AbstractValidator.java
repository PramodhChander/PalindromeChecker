package com.cme.assignment.palindromechecker.validation;

public abstract class AbstractValidator implements Validator {
    public abstract boolean isValid(Object value);
}
