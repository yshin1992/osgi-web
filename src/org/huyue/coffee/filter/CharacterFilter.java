package org.huyue.coffee.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.huyue.coffee.sys.filter.Filter;

/**
 * 字符编码过滤器
 * @author yshin1992
 *
 */
public class CharacterFilter implements Filter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("执行字符编码过滤器");
		return true;
	}

	@Override
	public void afterHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	public String[] getUrlPatterns() {
		return new String[]{"/*"};
	}



}
