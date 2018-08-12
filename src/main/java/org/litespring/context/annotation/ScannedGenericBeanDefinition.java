package org.litespring.context.annotation;/**
 * Created by LWD on 2018/8/12.
 */

import org.litespring.beans.factory.annotation.AnnotatedBeanDefinition;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.core.type.AnnotationMetadata;

/**
 * 扫描后，注册bean定义
 * @program: Litespring
 * @description:
 * @author: JC.Lin
 * @createDate: 2018-08-12 14:41
 */
public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {
    private AnnotationMetadata metadata;

    public ScannedGenericBeanDefinition(AnnotationMetadata annotationMetadata) {
        super();//创建bean定义
        this.metadata = annotationMetadata;
        setBeanClassName(this.metadata.getClassName());
    }

    public AnnotationMetadata getMetadata() {
        return metadata;
    }
}
