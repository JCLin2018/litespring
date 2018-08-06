package org.litespring.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.litespring.util.ClassUtils;

public class ClassPathResource implements Resource {

	private String path;
	private ClassLoader classLoader;

	public ClassPathResource(String path) {
		this(path, (ClassLoader) null);
	}
	public ClassPathResource(String path, ClassLoader classLoader) {
		this.path = path;
		this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
	}

	/**
	 * 获取文件流
	 * @return
	 * @throws IOException
	 */
	public InputStream getInputStream() throws IOException {
		//读取文件流
		InputStream is = this.classLoader.getResourceAsStream(this.path);
		if (is == null) {
			throw new FileNotFoundException(path + " cannot be opened");
		}
		return is;
	}

	/**
	 * 获取路径
	 * @return
	 */
	public String getDescription(){
		return this.path;
	}

}
