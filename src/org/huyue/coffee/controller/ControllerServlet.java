package org.huyue.coffee.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.huyue.coffee.Activator;
import org.huyue.coffee.sys.URLMapper;
import org.huyue.coffee.sys.cache.URLMapperCache;

public class ControllerServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//url路由
		String path = request.getRequestURI();
		
		String actionName = path.substring(Activator.contextName.length());
		
		URLMapper mapper = URLMapperCache.getURLMapper(actionName);
		try {
			Class<?> clazz = Class.forName(mapper.getClazz());
			Object actionInstance = clazz.newInstance();
			//注入request和response对象
			Method setRequest = clazz.getMethod("setRequest", HttpServletRequest.class);
			setRequest.invoke(actionInstance, request);
			Method setResponse = clazz.getMethod("setResponse", HttpServletResponse.class);
			setResponse.invoke(actionInstance, response);
			//执行action的具体方法
			Method method = clazz.getMethod(mapper.getMethod(), null);
			method.invoke(actionInstance, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
