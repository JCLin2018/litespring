package org.litespring.core.type;



import org.litespring.core.annotation.AnnotationAttributes;

import java.util.Set;

/**
 * 注解元素
 * @program: Litespring
 * @description:
 * @author: JC.Lin
 * @createDate: 2018-08-11 17:05
 */
public interface AnnotationMetadata extends ClassMetadata {
    /**
     * 获取注解里的参数类型
     */
    Set<String> getAnnotationTypes();

    /**
     * 根据注解类型判断是否存在该注解
     * @param annotationType
     */
    boolean hasAnnotation(String annotationType);

    /**
     * 根据注解类型获取注解参数
     * @param annotationType
     */
    AnnotationAttributes getAnnotationAttributes(String annotationType);

}
