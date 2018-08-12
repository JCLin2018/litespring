package org.litespring.context.annotation;/**
 * Created by LWD on 2018/8/12.
 */

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.BeanNameGenerator;
import org.litespring.core.io.Resource;
import org.litespring.core.io.support.PackageResourceLoader;
import org.litespring.core.type.classreading.MetadataReader;
import org.litespring.core.type.classreading.SimpleMetadataReader;
import org.litespring.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 类路径bean定义扫描
 *
 * @program: Litespring
 * @description:
 * @author: JC.Lin
 * @createDate: 2018-08-12 14:40
 */
public class ClassPathBeanDefinitionScanner {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final BeanDefinitionRegistry registry;

    private PackageResourceLoader resourceLoader = new PackageResourceLoader();

    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * 扫描包
     *
     * @param packagesToScan 扫描的包
     */
    public Set<BeanDefinition> doScan(String packagesToScan) {
        String[] basePackages = StringUtils.tokenizeToStringArray(packagesToScan, ",");
        Set<BeanDefinition> beanDefinitions = new LinkedHashSet<BeanDefinition>();
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                beanDefinitions.add(candidate);
                registry.registerBeanDefinition(candidate.getID(), candidate);//扫描到的类，都放到注册表里
            }
        }
        return beanDefinitions;
    }

    /**
     * @param basePackage
     * @return
     */
    private Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<BeanDefinition>();
        try {
            //一个包下有多个类文件
            Resource[] resources = this.resourceLoader.getResources(basePackage);
            for (Resource resource : resources) {
                try {
                    MetadataReader metadataReader = new SimpleMetadataReader(resource);
                    if (metadataReader.getAnnotationMetadata().hasAnnotation(Component.class.getName())) {//存在Component
                        ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader.getAnnotationMetadata());
                        String beanName = this.beanNameGenerator.generateBeanName(sbd, this.registry);
                        sbd.setID(beanName);
                        candidates.add(sbd);
                    }
                } catch (Throwable ex) {
                    throw new BeanDefinitionStoreException("Failed to read candidate component class: " + resource, ex);
                }
            }
        } catch (IOException ex) {
            throw new BeanDefinitionStoreException("I/O failure during classpath scanning", ex);
        }
        return candidates;

    }
}
