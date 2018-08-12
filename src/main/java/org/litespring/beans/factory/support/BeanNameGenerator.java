package org.litespring.beans.factory.support;/**
 * Created by LWD on 2018/8/12.
 */

import org.litespring.beans.BeanDefinition;

/**
 * 生成beanName
 * @program: Litespring
 * @description:
 * @author: JC.Lin
 * @createDate: 2018-08-12 14:57
 */
public interface BeanNameGenerator {

    /**
     * 为给定的bean定义生成bean名称
     * @param definition 要为其生成名称的bean定义
     * @param registry 给定定义应该在其中注册的bean定义注册表
     * @return the generated bean name
     */
    String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry);
}
