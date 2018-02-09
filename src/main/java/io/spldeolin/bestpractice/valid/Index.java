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
import org.springframework.core.annotation.AliasFor;

/**
 * <p>“可选数字范围”校验用注解</p>
 * <pre>
 * 支持类型：Integer ...
 * 规则：必须是{value}的其中之一，如果不指定{value}，则本注解不会产生任何作用
 * </pre>
 */
@Documented
@Constraint(validatedBy = {IndexValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface Index {

    String message() default "not choosable";

    Class<?>[] groups() default {};

    @AliasFor("options")
    int[] value() default {};

    @AliasFor("value")
    int[] options() default {};

    Class<? extends Payload>[] payload() default {};

}
