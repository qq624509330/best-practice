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
 * <p>“可选文字范围”校验用注解</p>
 * <pre>
 * 支持类型：String
 * 规则：必须是{value}的其中之一，如果不指定{value}，则本注解不会产生任何作用
 * </pre>
 */
@Documented
@Constraint(validatedBy = {ChooseValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface Choose {

    String message() default "not choosable";

    Class<?>[] groups() default {};

    @AliasFor("options")
    String[] value() default {};

    @AliasFor("value")
    String[] options() default {};

    boolean ignoreCase() default true;

    Class<? extends Payload>[] payload() default {};

}
