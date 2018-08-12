package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.PropertyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean定义实现类
 */
public class GenericBeanDefinition implements BeanDefinition {
	private String id;//beanID
	private String beanClassName;//bean的全类名
	private boolean singleton = true;
	private boolean prototype = false;
	private String scope = SCOPE_DEFAULT;
	//Property对象
	List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();
	//constructor-arg对象
	private ConstructorArgument constructorArgument = new ConstructorArgument();

	public GenericBeanDefinition() {}
	public GenericBeanDefinition(String id, String beanClassName) {
		this.id = id;
		this.beanClassName = beanClassName;
	}
	public String getBeanClassName() {
		return this.beanClassName;
	}
	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
	}

	public boolean isSingleton() {
		return this.singleton;
	}

	public boolean isPrototype() {
		return this.prototype;
	}

	public String getScope() {
		return this.scope;
	}

	/**
	 * 设置单例
	 * @param scope
	 */
	public void setScope(String scope) {
		this.scope = scope;
		this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
		this.prototype = SCOPE_PROTOTYPE.equals(scope);
	}

	/**
	 * 获取Property对象集合
	 * @return
	 */
	public List<PropertyValue> getPropertyValues(){
		return this.propertyValues;
	}

	/**
	 * 获取constructor-arg对象
	 * @return
	 */
	public ConstructorArgument getConstructorArgument() {
		return this.constructorArgument;
	}
	/**
	 * 获取beanId
	 * @return
	 */
	public String getID() {
		return this.id;
	}

	/**
	 * 获取beanId
	 * @param beanId
	 */
	public void setID(String beanId) {
		this.id = beanId;
	}
	/**
	 * 判断是否存在构造器参数
	 */
	public boolean hasConstructorArgumentValues() {
		return !this.constructorArgument.isEmpty();
	}
}
