package org.litespring.core.io.support;/**
 * Created by LWD on 2018/8/12.
 */

import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;
import org.litespring.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @program: Litespring
 * @description:
 * @author: JC.Lin
 * @createDate: 2018-08-12 14:54
 */
public class PackageResourceLoader {
    private static final Logger logger = LoggerFactory.getLogger(PackageResourceLoader.class);

    private final ClassLoader classLoader;

    public PackageResourceLoader() {
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    public PackageResourceLoader(ClassLoader classLoader) {
        Assert.notNull(classLoader, "ResourceLoader must not be null");
        this.classLoader = classLoader;
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public Resource[] getResources(String basePackage) throws IOException {
        Assert.notNull(basePackage, "basePackage  must not be null");
        String location = ClassUtils.convertClassNameToResourcePath(basePackage);//將.号换成/号
        ClassLoader cl = getClassLoader();
        URL url = cl.getResource(location);
        File rootDir = new File(url.getFile());

        Set<File> matchingFiles = retrieveMatchingFiles(rootDir);
        Resource[] result = new Resource[matchingFiles.size()];
        int i = 0;
        for (File file : matchingFiles) {
            result[i++] = new FileSystemResource(file);
        }
        return result;
    }

    protected Set<File> retrieveMatchingFiles(File rootDir) throws IOException {
        if (!rootDir.exists()) {
            // Silently skip non-existing directories.
            if (logger.isDebugEnabled()) {
                logger.debug("Skipping [" + rootDir.getAbsolutePath() + "] because it does not exist");
            }
            return Collections.emptySet();
        }
        if (!rootDir.isDirectory()) {//不是一个目录
            // Complain louder if it exists but is no directory.
            if (logger.isWarnEnabled()) {
                logger.warn("Skipping [" + rootDir.getAbsolutePath() + "] because it does not denote a directory");
            }
            return Collections.emptySet();
        }
        if (!rootDir.canRead()) {//不可以读取此文件
            if (logger.isWarnEnabled()) {
                logger.warn("Cannot search for matching files underneath directory [" + rootDir.getAbsolutePath() +
                        "] because the application is not allowed to read the directory");
            }
            return Collections.emptySet();
        }
        /*
		String fullPattern = StringUtils.replace(rootDir.getAbsolutePath(), File.separator, "/");
		if (!pattern.startsWith("/")) {
			fullPattern += "/";
		}
		fullPattern = fullPattern + StringUtils.replace(pattern, File.separator, "/");
		*/
        Set<File> result = new LinkedHashSet<File>(8);
        doRetrieveMatchingFiles(rootDir, result);
        return result;
    }

    /**
     * 递归调用
     */
    protected void doRetrieveMatchingFiles(File dir, Set<File> result) throws IOException {
        File[] dirContents = dir.listFiles();//下一级文件夹
        if (dirContents == null) {
            if (logger.isWarnEnabled()) {
                logger.warn("Could not retrieve contents of directory [" + dir.getAbsolutePath() + "]");
            }
            return;
        }
        for (File content : dirContents) {
            if (content.isDirectory()) {
                if (!content.canRead()) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Skipping subdirectory [" + dir.getAbsolutePath() + "] because the application is not allowed to read the directory");
                    }
                } else {//可读，下一个文件夹
                    doRetrieveMatchingFiles(content, result);
                }
            } else {//不是一个目录，是一个文件
                result.add(content);
            }

        }
    }
}
