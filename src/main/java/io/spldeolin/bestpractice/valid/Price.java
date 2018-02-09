package io.spldeolin.bestpractice.valid;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * <p>“价格”校验用注解</p>
 * <pre>
 * 支持类型：BigDecimal、String
 * 规则：整数部分不能超过8{integerLength}位，且小数部分不能超过2{decimalLength}位
 * </pre>
 */
@Documented
@Constraint(validatedBy = {PriceBigDecimalValidator.class, PriceStringValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface Price {

    String message() default "invalid price";

    Class<?>[] groups() default {};

    int integerLength() default 8;

    int decimalLength() default 2;

    Class<? extends Payload>[] payload() default {};

}
