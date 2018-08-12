package org.litespring.core.type.classreading;/**
 * Created by LWD on 2018/8/12.
 */

import org.litespring.core.io.Resource;
import org.litespring.core.type.AnnotationMetadata;
import org.litespring.core.type.ClassMetadata;
import org.springframework.asm.ClassReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ASM读取class文件
 * @program: Litespring
 * @description:
 * @author: JC.Lin
 * @createDate: 2018-08-12 14:52
 */
public class SimpleMetadataReader implements MetadataReader {
    private final Resource resource;

    private final ClassMetadata classMetadata;

    private final AnnotationMetadata annotationMetadata;

    /**
     *
     * @param resource
     * @throws IOException
     */
    public SimpleMetadataReader(Resource resource) throws IOException {
        InputStream is = new BufferedInputStream(resource.getInputStream());
        ClassReader classReader;

        try {
            classReader = new ClassReader(is);
        } finally {
            is.close();
        }

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        //调用accept方法后，asm开始读取.class文件。读取过程中，当开始读取时会调用visit()方法，
        //读取属性时，会调用visitField()方法
        //读取方法时，会调用visitMethod()方法
        //读取完后，会调用visitEnd()方法
        classReader.accept(visitor, ClassReader.SKIP_DEBUG);

        this.annotationMetadata = visitor;
        this.classMetadata = visitor;
        this.resource = resource;
    }


    public Resource getResource() {
        return this.resource;
    }

    public ClassMetadata getClassMetadata() {
        return this.classMetadata;
    }

    public AnnotationMetadata getAnnotationMetadata() {
        return this.annotationMetadata;
    }
}
