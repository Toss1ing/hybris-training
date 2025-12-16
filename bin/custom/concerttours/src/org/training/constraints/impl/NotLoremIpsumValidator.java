package org.training.constraints.impl;

import org.training.constraints.NotLoremIpsum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotLoremIpsumValidator implements ConstraintValidator<org.training.constraints.NotLoremIpsum, String> {
    @Override
    public void initialize(NotLoremIpsum constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty() && !value.toLowerCase().startsWith("lorem ipsum");
    }
}
