package org.litespring.core.type;

/**
 * 类元素
 */
public interface ClassMetadata {
    /**
     * 返回类的名称
     */
    String getClassName();
    /**
     * 该类是否有实现接口
     */
    boolean isInterface();
    /**
     * 该类是否抽象
     */
    boolean isAbstract();
    /**
     * 该类是否标记‘final’
     */
    boolean isFinal();
    /**
     * 该类是否有父类
     */
    boolean hasSuperClass();
    /**
     * 返回该类的父类名称
     */
    String getSuperClassName();
    /**
     * 返回类的接口
     */
    String[] getInterfaceNames();
}
