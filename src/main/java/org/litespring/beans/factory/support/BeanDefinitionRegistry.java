package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

/**
 * bean定义登记
 */
public interface BeanDefinitionRegistry {
	BeanDefinition getBeanDefinition(String beanID);
	void registerBeanDefinition(String beanID, BeanDefinition bd);
}
