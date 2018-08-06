package org.litespring.beans;

import java.util.List;


/**
 * bean的定义
 */
public interface BeanDefinition {
	public static final String SCOPE_SINGLETON = "singleton";
	public static final String SCOPE_PROTOTYPE = "prototype";
	public static final String SCOPE_DEFAULT = "";

	public String getBeanClassName();

	public boolean isSingleton();

	public boolean isPrototype();

	String getScope();

	/**
	 * 设置单例
	 * @param scope
	 */
	void setScope(String scope);
	/**
	 * 获取Property对象集合
	 * @return
	 */
	public List<PropertyValue> getPropertyValues();
	/**
	 * 获取constructor-arg对象
	 * @return
	 */
	public ConstructorArgument getConstructorArgument();
	/**
	 * 获取beanid
	 * @return
	 */
	public String getID();
	/**
	 * 判断是否存在构造器参数
	 */
	public boolean hasConstructorArgumentValues();
}
