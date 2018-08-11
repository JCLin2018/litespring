package org.litespring.core.type.classreading;/**
 * Created by LWD on 2018/8/11.
 */

import org.litespring.core.type.ClassMetadata;
import org.litespring.util.ClassUtils;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Opcodes;
import org.springframework.asm.SpringAsmInfo;

/**
 * 读取类信息，重写asm
 * @program: Litespring
 * @description:
 * @author: JC.Lin
 * @createDate: 2018-08-11 16:19
 */
public class ClassMetadataReadingVisitor extends ClassVisitor implements ClassMetadata {

    private String className;

    private boolean isInterface;

    private boolean isAbstract;

    private boolean isFinal;

    private String superClassName;

    private String[] interfaces;

    public ClassMetadataReadingVisitor() {
        super(SpringAsmInfo.ASM_VERSION);
    }

    /**
     * 当asm读取class文件后，调用这个方法
     * @param version java版本号
     * @param access
     * @param name 字节码类名(Ljava.lang.String) 转换成 java.lang.String
     * @param signature
     * @param superName 超类名称(Ljava.lang.Object)
     * @param inerfaces
     */
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] inerfaces) {
        this.className = ClassUtils.convertResourcePathToClassName(name);
        this.isInterface = ((access & Opcodes.ACC_INTERFACE) != 0);
        this.isAbstract = ((access & Opcodes.ACC_ABSTRACT) != 0);
        this.isFinal = ((access & Opcodes.ACC_FINAL) != 0);
        if(superName != null){
            this.superClassName = ClassUtils.convertResourcePathToClassName(superName);
        }
        if(interfaces != null && interfaces.length > 0) {
            this.interfaces = new String[interfaces.length];
            for (int i = 0; i < interfaces.length; i++) {
                this.interfaces[i] = ClassUtils.convertResourcePathToClassName(interfaces[i]);
            }
        }
    }

    @Override
    public String getClassName() {
        return this.className;
    }

    @Override
    public boolean isInterface() {
        return this.isInterface;
    }

    @Override
    public boolean isAbstract() {
        return this.isAbstract;
    }

    public boolean isConcreate() {
        return !(this.isInterface || this.isAbstract);
    }

    @Override
    public boolean isFinal() {
        return this.isFinal;
    }

    @Override
    public boolean hasSuperClass() {
        return (this.superClassName != null);
    }

    @Override
    public String getSuperClassName() {
        return this.superClassName;
    }

    @Override
    public String[] getInterfaceNames() {
        if(this.isInterface())
            return this.interfaces;
        else
            return new String[]{};
    }
}
