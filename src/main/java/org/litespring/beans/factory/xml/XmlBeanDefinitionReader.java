package org.litespring.beans.factory.xml;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.core.io.Resource;
import org.litespring.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class XmlBeanDefinitionReader {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public static final String ID_ATTRIBUTE = "id";

	public static final String CLASS_ATTRIBUTE = "class";

	public static final String SCOPE_ATTRIBUTE = "scope";

	public static final String PROPERTY_ELEMENT = "property";

	public static final String REF_ATTRIBUTE = "ref";

	public static final String VALUE_ATTRIBUTE = "value";

	public static final String NAME_ATTRIBUTE = "name";

	public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";

	public static final String TYPE_ATTRIBUTE = "type";

	private BeanDefinitionRegistry registry;

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry){
		this.registry = registry;
	}

	/**
	 * 读取bean.xml配置文件
	 * @param resource
	 */
	public void loadBeanDefinitions(Resource resource){
		InputStream is = null;
		try{
			is = resource.getInputStream();
			//dom4j读取xml信息
			SAXReader reader = new SAXReader();
			Document doc = reader.read(is);
			
			Element root = doc.getRootElement(); //<beans>
			Iterator<Element> iter = root.elementIterator();
			while(iter.hasNext()){
				Element ele = (Element)iter.next();//<beans>标签下所有标签
				String id = ele.attributeValue(ID_ATTRIBUTE);//获取<bean id="">
				String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);//获取<bean class="">
				BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
				if (ele.attribute(SCOPE_ATTRIBUTE) != null) {//xml中是否对<bean>标签定义单例
					bd.setScope(ele.attributeValue(SCOPE_ATTRIBUTE));					
				}
				parseConstructorArgElements(ele,bd);
				parsePropertyElement(ele,bd); 
				this.registry.registerBeanDefinition(id, bd);
			}
		} catch (Exception e) {		
			throw new BeanDefinitionStoreException("IOException parsing XML document from " + resource.getDescription(),e);
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * 构造器注入
	 * @param beanEle
	 * @param bd
	 */
	public void parseConstructorArgElements(Element beanEle, BeanDefinition bd) {
		Iterator iter = beanEle.elementIterator(CONSTRUCTOR_ARG_ELEMENT);//<bean>下的所有<constructor-arg>标签
		while(iter.hasNext()){
			Element ele = (Element)iter.next();//拿到<constructor-arg>标签
			parseConstructorArgElement(ele, bd);			
		}
	}

	/**
	 * 对<constructor-arg>标签进行操作
	 * @param ele
	 * @param bd
	 */
	public void parseConstructorArgElement(Element ele, BeanDefinition bd) {
		String typeAttr = ele.attributeValue(TYPE_ATTRIBUTE);//type属性
		String nameAttr = ele.attributeValue(NAME_ATTRIBUTE);//name属性
		Object value = parsePropertyValue(ele, bd, null);//获取ref属性或者value属性
		ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);
		//type属性
		if (StringUtils.hasLength(typeAttr)) {
			valueHolder.setType(typeAttr);
		}
		//name属性
		if (StringUtils.hasLength(nameAttr)) {
			valueHolder.setName(nameAttr);
		}
		//添加到构造器参数中
		bd.getConstructorArgument().addArgumentValue(valueHolder);
	}

	/**
	 * 处理setting注入
	 * @param beanElem
	 * @param bd
	 */
	public void parsePropertyElement(Element beanElem, BeanDefinition bd) {
		Iterator iter= beanElem.elementIterator(PROPERTY_ELEMENT);//获取property标签
		while(iter.hasNext()){
			Element propElem = (Element)iter.next();
			String propertyName = propElem.attributeValue(NAME_ATTRIBUTE);//name属性
			if (!StringUtils.hasLength(propertyName)) {
				logger.trace("Tag 'property' must have a 'name' attribute");
				return;
			}
			Object val = parsePropertyValue(propElem, bd, propertyName);//获得ref属性或者value属性
			PropertyValue pv = new PropertyValue(propertyName, val);
			//添加到setting的属性列表中
			bd.getPropertyValues().add(pv);
		}
		
	}

	/**
	 * 处理<property>标签的ref属性和value属性
	 * @param ele
	 * @param bd bean的定义
	 * @param propertyName
	 * @return
	 */
	public Object parsePropertyValue(Element ele, BeanDefinition bd, String propertyName) {
		String elementName = (propertyName != null) ?
				"<property> element for property '" + propertyName + "'" : "<constructor-arg> element";

		boolean hasRefAttribute = (ele.attribute(REF_ATTRIBUTE) != null);//是否存在ref属性
		boolean hasValueAttribute = (ele.attribute(VALUE_ATTRIBUTE) != null);//是否存在value属性
		
		if (hasRefAttribute) {
			String refName = ele.attributeValue(REF_ATTRIBUTE);
			if (!StringUtils.hasText(refName)) {
				logger.error(elementName + " contains empty 'ref' attribute");
			}
			RuntimeBeanReference ref = new RuntimeBeanReference(refName);
			return ref;
		} else if (hasValueAttribute) {
			TypedStringValue valueHolder = new TypedStringValue(ele.attributeValue(VALUE_ATTRIBUTE));
			return valueHolder;
		} else {
			throw new RuntimeException(elementName + " must specify a ref or value");
		}
	}
}

