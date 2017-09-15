package org.huyue.coffee.sys.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 三段式模板
 * prepare-confirm-submit
 * @author yshin1992
 *
 */
public abstract class Action {

	protected HttpServletRequest request;
	
	protected HttpServletResponse response;
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 在httpRequest请求中添加参数
	 * @param attrName
	 * @param attrValue
	 */
	public void addAttr(String attrName,Object attrValue){
		request.setAttribute(attrName, attrValue);
	}
	
	/**
	 * 将Map中的键值对添加HttpRequest中
	 * @param attrMap
	 */
	public void addAttrMap(Map<String,Object> attrMap){
		if(null != attrMap && attrMap.size() > 0){
			Set<String> keySet = attrMap.keySet();
			for(String key:keySet){
				this.addAttr(key, attrMap.get(key));
			}
		}
	}
	
	/**
	 * 页面跳转
	 * @param page
	 * @throws ServletException
	 * @throws IOException
	 */
	public void forward(String page){
		try {
			request.getRequestDispatcher(page).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String param(String parameter){
		return request.getParameter(parameter);
	}
	
	public String[] paramValues(String parameter){
		return request.getParameterValues(parameter);
	}
}
