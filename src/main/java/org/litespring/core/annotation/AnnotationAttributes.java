package org.litespring.core.annotation;/**
 * Created by LWD on 2018/8/11.
 */

import org.litespring.util.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 注解参数集合
 * @program: Litespring
 * @description:
 * @author: JC.Lin
 * @createDate: 2018-08-11 17:11
 */
public class AnnotationAttributes extends LinkedHashMap<String, Object> {
    public AnnotationAttributes() {}

    /**
     * 创建
     * @param initialCapacity
     */
    public AnnotationAttributes(int initialCapacity) {
        super(initialCapacity);
    }

    public AnnotationAttributes(Map<String, Object> map) {
        super(map);
    }

    /**
     * 根据参数名获取参数值
     * @param attributeName
     */
    public String getString(String attributeName) {
        return doGet(attributeName, String.class);
    }
    /**
     * 根据参数名获取参数值
     * @param attributeName
     */
    public String[] getStringArray(String attributeName) {
        return doGet(attributeName, String[].class);
    }
    /**
     * 根据参数名获取参数值
     * @param attributeName
     */
    public boolean getBoolean(String attributeName) {
        return doGet(attributeName, Boolean.class);
    }
    /**
     * 根据参数名获取参数值
     * @param attributeName
     */
    @SuppressWarnings("unchecked")
    public <N extends Number> N getNumber(String attributeName) {
        return (N) doGet(attributeName, Integer.class);
    }

    /**
     * 根据参数名获取枚举类型
     * @param attributeName
     * @param <E>
     */
    @SuppressWarnings("unchecked")
    public <E extends Enum<?>> E getEnum(String attributeName) {
        return (E) doGet(attributeName, Enum.class);
    }

    /**
     * 根据参数名，获取类
     * @param attributeName
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public <T> Class<? extends T> getClass(String attributeName) {
        return doGet(attributeName, Class.class);
    }

    public Class<?>[] getClassArray(String attributeName) {
        return doGet(attributeName, Class[].class);
    }

    @SuppressWarnings("unchecked")
    private <T> T doGet(String attributeName, Class<T> expectedType) {
        Object value = this.get(attributeName);
        Assert.notNull(value, String.format("Attribute '%s' not found", attributeName));
        return (T) value;
    }


}
