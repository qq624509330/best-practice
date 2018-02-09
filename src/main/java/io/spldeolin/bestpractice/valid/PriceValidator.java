package io.spldeolin.bestpractice.valid;

import java.math.BigDecimal;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceValidator implements ConstraintValidator<Price, BigDecimal> {

    private int integerLength;

    private int decimalLength;

    @Override
    public void initialize(Price constraintAnnotation) {
        decimalLength = constraintAnnotation.decimalLength();
        integerLength = constraintAnnotation.integerLength();
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        String[] parts = value.toString().split("\\.");
        return parts[0].length() <= integerLength && parts[1].length() <= decimalLength;
    }

}
