package org.litespring.beans.factory.config;

/**
 * 处理value属性
 */
public class TypedStringValue {
	private String value;
	public TypedStringValue(String value) {
		this.value = value;
	}
	public String getValue() {
		return this.value;
	}
}
