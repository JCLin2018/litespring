package org.litespring.context.support;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassUtils;

/**
 * 抽象的上下文
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

	private DefaultBeanFactory factory = null;
	private ClassLoader beanClassLoader;

	public AbstractApplicationContext(String configFile){
		this(configFile, null);
	}
	public AbstractApplicationContext(String configFile, ClassLoader beanClassLoader){
		this.setBeanClassLoader(beanClassLoader);
		factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);	
		Resource resource = this.getResourceByPath(configFile);
		reader.loadBeanDefinitions(resource);
		factory.setBeanClassLoader(this.getBeanClassLoader());
	}

	/**
	 * 根据beanID获取bean对象
	 * @param beanID
	 * @return
	 */
	public Object getBean(String beanID) {
		return factory.getBean(beanID);
	}

	/**
	 * 获取资源
	 * @param path
	 */
	protected abstract Resource getResourceByPath(String path);
	
	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader = beanClassLoader;
	}

    public ClassLoader getBeanClassLoader() {
		return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
	}

}
