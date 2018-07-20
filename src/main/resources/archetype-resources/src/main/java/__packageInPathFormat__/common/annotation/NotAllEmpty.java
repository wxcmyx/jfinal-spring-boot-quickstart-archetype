package ${package}.common.annotation;

import ${package}.common.validator.NotAllEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Lye
 * @description 用于校验多个字段不同时为Null
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotAllEmptyValidator.class)
@Documented
public @interface NotAllEmpty {
    String message() default "不能同时为空";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 字段名称
     *
     * @return
     */
    String[] fieldNames();

}
