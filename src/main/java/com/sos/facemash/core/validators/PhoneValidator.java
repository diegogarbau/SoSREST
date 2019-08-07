package com.sos.facemash.core.validators;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PhoneValidator implements ConstraintValidator<PhoneConstraint, Integer> {

    @Override
    public void initialize(PhoneConstraint phone) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return (value.toString().length() == 9);

    }

}