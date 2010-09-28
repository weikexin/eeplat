package com.ken.finance.gzip;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Autour: Ken.xu
 * 
 * Version: V1.0
 * 
 * Description BEA WebLogic下GZIP过滤器
 * 
 */
public class CompressionFilter implements Filter {
	private FilterConfig config;

	/**
	 * 如果浏览器支持解压缩,则压缩该代码
	 * 
	 * @throws IOException
	 *             ServletException if an error occurs with the
	 *             {@link GZIPOutputStream}. 如果需要开启该过滤器,在web.xml中加入此代码 <filter>
	 *             <filter-name>gzip</filter-name>
	 *             <filter-class>com.ken.finance.gzip</filter-class> </filter>
	 *             <filter-mapping> <filter-name>gzip</filter-name>
	 *             <url-pattern>*.jsp</url-pattern> </filter-mapping>
	 */
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	private static final String HAS_RUN_KEY = CompressionFilter.class.getName()
			+ ".HAS_RUN";

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		boolean compress = false;
		if (request.getAttribute(HAS_RUN_KEY) == null
				&& request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			Enumeration headers = httpRequest.getHeaders("Accept-Encoding");
			while (headers.hasMoreElements()) {
				String value = (String) headers.nextElement();
				if (value.indexOf("gzip") != -1) {
					compress = true;
				}
			}
		}
		request.setAttribute(HAS_RUN_KEY, "true");
		if (compress) {
			// 如果浏览器支持则压缩
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.addHeader("Content-Encoding", "gzip");
			CompressionResponse compressionResponse = new CompressionResponse(
					httpResponse);
			chain.doFilter(request, compressionResponse);
			compressionResponse.finish();
		} else {
			// 如果浏览器不支持则不压缩
			chain.doFilter(request, response);
		}
		System.out.println("Compression Filter Test Servlet");// 查看是否执行该过滤器
	}

	public void destroy() {
		config = null;
	}
}
