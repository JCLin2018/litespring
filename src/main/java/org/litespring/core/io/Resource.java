package org.litespring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源接口
 */
public interface Resource {
	/**
	 * 获取文件流
	 * @return
	 * @throws IOException
	 */
	public InputStream getInputStream() throws IOException;

	/**
	 * 获取路径
	 * @return
	 */
	public String getDescription();
}
