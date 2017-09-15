package org.huyue.coffee.sys.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义Filter
 * 前置处理，后置处理实现类实现
 * @author yshin1992
 *
 */
public interface Filter {

	String[] getUrlPatterns();
	
	boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	void afterHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
}
