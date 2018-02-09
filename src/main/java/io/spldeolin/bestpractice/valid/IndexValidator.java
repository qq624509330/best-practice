package io.spldeolin.bestpractice.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IndexValidator implements ConstraintValidator<Index, Integer> {

    private int[] options;

    @Override
    public void initialize(Index constraintAnnotation) {
        options = constraintAnnotation.value();
        if (options.length == 0) {
            options = constraintAnnotation.options();
        }
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (options.length == 0) {
            return true;
        }
        for (int option : options) {
            if (value.equals(option)) {
                return true;
            }
        }
        return false;
    }

}
