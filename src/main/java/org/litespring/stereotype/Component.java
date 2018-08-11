package org.litespring.stereotype;/**
 * Created by LWD on 2018/8/9.
 */

import java.lang.annotation.*;

/**
 * @program: Litespring
 * @description: 标记该类注入到IOC容器
 * @author: JC.Lin
 * @createDate: 2018-08-09 23:54
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     * @return the suggested component name, if any
     */
    String value() default "";
}
