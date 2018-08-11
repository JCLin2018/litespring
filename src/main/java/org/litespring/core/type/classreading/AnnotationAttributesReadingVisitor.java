package org.litespring.core.type.classreading;

import org.litespring.core.annotation.AnnotationAttributes;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.SpringAsmInfo;

import java.util.Map;

/**
 * 注解参数读取
 * @program: Litespring
 * @description:
 * @author: JC.Lin
 * @createDate: 2018-08-11 17:27
 */
public final class AnnotationAttributesReadingVisitor extends AnnotationVisitor {
    private final String annotationType;//注解类型

    private final Map<String, AnnotationAttributes> attributesMap;//注解的参数

    private AnnotationAttributes attributes = new AnnotationAttributes();

    public AnnotationAttributesReadingVisitor(String annotationType, Map<String, AnnotationAttributes> attributesMap) {
        super(SpringAsmInfo.ASM_VERSION);
        this.annotationType = annotationType;
        this.attributesMap = attributesMap;
    }
    /**
     * 开始读取注解里面的属性后
     */
    public void visit(String attributeName, Object attributeValue) {
        this.attributes.put(attributeName, attributeValue);
    }
    /**
     * 读取完这个注解里的所有属性后就调用
     */
    @Override
    public final void visitEnd(){
        this.attributesMap.put(this.annotationType, this.attributes);
    }

}
