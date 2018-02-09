package io.spldeolin.bestpractice.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceStringValidator implements ConstraintValidator<Price, String> {

    private int integerLength;

    private int decimalLength;

    @Override
    public void initialize(Price constraintAnnotation) {
        decimalLength = constraintAnnotation.decimalLength();
        integerLength = constraintAnnotation.integerLength();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        String[] parts = value.split("\\.");
        return parts[0].length() <= integerLength && parts[1].length() <= decimalLength;
    }

}
