package org.litespring.beans;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * xml中构造器信息（<constructor-arg>标签）
 */
public class ConstructorArgument {
	//构造器注入参数
	private final List<ValueHolder> argumentValues = new LinkedList<ValueHolder>();

	public ConstructorArgument() {}

	/**
	 * 添加参数
	 * @param valueHolder
	 */
	public void addArgumentValue(ValueHolder valueHolder) {
		this.argumentValues.add(valueHolder);
	}

	/**
	 * 获取参数集合
	 * @return
	 */
	public List<ValueHolder> getArgumentValues() {
		return Collections.unmodifiableList(this.argumentValues);
	}

	/**
	 * 获取参数个数
	 * @return
	 */
	public int getArgumentCount() {
		return this.argumentValues.size();
	}

	/**
	 * 判断是否没有参数
	 * @return
	 */
	public boolean isEmpty() {
		return this.argumentValues.isEmpty();
	}

	/**
	 * 清除参数集合
	 */
	public void clear() {
		this.argumentValues.clear();
	}

	/**
	 * 参数实体（<constructor-arg>标签的属性）
	 */
	public static class ValueHolder{

		private Object value;//value属性

		private String type;//type属性

		private String name;//name属性

		public ValueHolder(Object value) {
			this.value = value;
		}

		public ValueHolder(Object value, String type) {
			this.value = value;
			this.type = type;
		}

		public ValueHolder(Object value, String type, String name) {
			this.value = value;
			this.type = type;
			this.name = name;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		public Object getValue() {
			return this.value;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getType() {
			return this.type;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}

}
