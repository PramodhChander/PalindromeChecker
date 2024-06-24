package com.cme.assignment.palindromechecker.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class CustomValidateAnnotationValidator implements ConstraintValidator<Validate, Object> {

    @Autowired
    private ApplicationContext applicationContext;

    private Validator validator;

    @Override
    public void initialize(Validate constraintAnnotation) {
        this.validator = applicationContext.getBean(constraintAnnotation.fieldValidator());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return validator.isValid(value);
    }
}