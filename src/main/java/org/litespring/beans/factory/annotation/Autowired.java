package org.litespring.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @program: Litespring
 * @description: 注入注解
 * @author: JC.Lin
 * @createDate: 2018-08-09 23:52
 */
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    /**
     * Declares whether the annotated dependency is required.
     * <p>Defaults to {@code true}.
     */
    boolean required() default true;
}
