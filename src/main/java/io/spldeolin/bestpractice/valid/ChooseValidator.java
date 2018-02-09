package io.spldeolin.bestpractice.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class ChooseValidator implements ConstraintValidator<Choose, String> {

    private String[] options;

    private boolean ignoreCase;

    @Override
    public void initialize(Choose constraintAnnotation) {
        options = constraintAnnotation.value();
        if (options.length == 0) {
            options = constraintAnnotation.options();
        }
        ignoreCase = constraintAnnotation.ignoreCase();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (options.length == 0) {
            return true;
        }
        for (String option : options) {
            if (ignoreCase) {
                if (StringUtils.equalsIgnoreCase(option, value)) {
                    return true;
                }
            } else {
                if (StringUtils.equals(option, value)) {
                    return true;
                }
            }
        }
        return false;
    }

}
