package org.litespring.beans.factory.annotation;/**
 * Created by LWD on 2018/8/12.
 */

import org.litespring.beans.BeanDefinition;
import org.litespring.core.type.AnnotationMetadata;

/**
 * bean的定义
 * @program: Litespring
 * @description:
 * @author: JC.Lin
 * @createDate: 2018-08-12 15:02
 */
public interface AnnotatedBeanDefinition extends BeanDefinition {
    AnnotationMetadata getMetadata();
}
