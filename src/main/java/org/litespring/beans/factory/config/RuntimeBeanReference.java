package org.litespring.beans.factory.config;

/**
 * 处理ref属性
 */
public class RuntimeBeanReference {
	private final String beanName;
	public RuntimeBeanReference(String beanName) {
		this.beanName = beanName;
	}
	public String getBeanName() {
		return this.beanName;
	}
}
