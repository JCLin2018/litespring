package org.litespring.core.type.classreading;/**
 * Created by LWD on 2018/8/12.
 */

import org.litespring.core.io.Resource;
import org.litespring.core.type.AnnotationMetadata;
import org.litespring.core.type.ClassMetadata;

/**
 * @program: Litespring
 * @description:
 * @author: JC.Lin
 * @createDate: 2018-08-12 14:51
 */
public interface MetadataReader {
    /**
     * Return the resource reference for the class file.
     */
    Resource getResource();

    /**
     * Read basic class metadata for the underlying class.
     */
    ClassMetadata getClassMetadata();

    /**
     * Read full annotation metadata for the underlying class,
     * including metadata for annotated methods.
     */
    AnnotationMetadata getAnnotationMetadata();
}
