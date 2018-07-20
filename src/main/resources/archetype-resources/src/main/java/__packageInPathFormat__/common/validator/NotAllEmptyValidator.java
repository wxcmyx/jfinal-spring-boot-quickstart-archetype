package ${package}.common.validator;

import ${package}.common.annotation.NotAllEmpty;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 * @author Lye
 * @description 用于校验多个字段不同时为空
 */
public class NotAllEmptyValidator implements ConstraintValidator<NotAllEmpty, Object> {

    private String[] fieldNames;

    @Override
    public void initialize(NotAllEmpty constraintAnnotation) {
        fieldNames = constraintAnnotation.fieldNames();
    }

    @Override
    public boolean isValid(Object bean, ConstraintValidatorContext context) {
        Class<?> beanClass = bean.getClass();
        for (String fieldName : fieldNames) {
            try {
                Field field = beanClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                final Object value = field.get(bean);
                if (value != null) {
                    //字符串判断
                    if (value instanceof String) {
                        String strValue = (String) value;
                        if (StringUtils.isNotBlank(strValue)) {
                            return true;
                        }
                        //集合判断
                    } else if (value instanceof Collection) {
                        Collection collectionValue = (Collection) value;
                        if (!collectionValue.isEmpty()) {
                            return true;
                        }
                        //Map判断
                    } else if (value instanceof Map) {
                        Map mapValue = (Map) value;
                        if (!mapValue.isEmpty()) {
                            return true;
                        }
                    }
                }
            } catch (Exception e) {

            }
        }
        return false;
    }
}
