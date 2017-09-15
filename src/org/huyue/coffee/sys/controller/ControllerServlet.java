package org.huyue.coffee.sys.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.huyue.coffee.Activator;
import org.huyue.coffee.sys.URLMapper;
import org.huyue.coffee.sys.cache.URLMapperCache;
import org.huyue.coffee.sys.filter.Filter;
import org.huyue.coffee.sys.util.UrlPatternUtil;

public class ControllerServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private List<Filter> filters;
	
	public ControllerServlet(List<Filter> filters) {
		this.filters = filters;
	}
	
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
			//执行过滤器列表，当过滤器中存在执行返回false时，不继续执行
			if(null != filters && filters.size() > 0){
				for(Filter filter : filters){
					String[] patterns = filter.getUrlPatterns();
					for(String pattern : patterns){
						if(UrlPatternUtil.pattern(pattern, actionName) && !filter.preHandle(request, response)){
							return;
						}
					}
					
				}
			}
			
			
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
			
			//后置处理
			if(null != filters && filters.size() > 0){
				for(Filter filter : filters){
					String[] patterns = filter.getUrlPatterns();
					for(String pattern : patterns){
						if(UrlPatternUtil.pattern(pattern, actionName)){
							filter.afterHandle(request, response);
						}
					}
					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
