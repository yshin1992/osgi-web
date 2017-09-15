package org.huyue.coffee;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;

import org.eclipse.equinox.http.helper.BundleEntryHttpContext;
import org.eclipse.equinox.http.helper.ContextPathServletAdaptor;
import org.eclipse.equinox.jsp.jasper.JspServlet;
import org.huyue.coffee.filter.CharacterFilter;
import org.huyue.coffee.sys.URLMapper;
import org.huyue.coffee.sys.cache.URLMapperCache;
import org.huyue.coffee.sys.controller.ControllerServlet;
import org.huyue.coffee.sys.filter.Filter;
import org.huyue.coffee.sys.util.URLMapperReader;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;
import org.xml.sax.InputSource;

public class Activator implements BundleActivator {

	private static BundleContext context;

	public static String contextName = "/coffee";
	
	static BundleContext getContext() {
		return context;
	}

	private ServiceTracker httpServiceTracker;

	public void start(BundleContext context) throws Exception {
//		PropertyConfigurator.configure(context.getBundle().getEntry("resources/log4j.properties"));
		
		httpServiceTracker = new HttpServiceTracker(context);
		httpServiceTracker.open();
		//从配置文件中读取action列表
		//后期改用注解
		URL entry = context.getBundle().getEntry("resources/url-mapper.xml");
		InputSource source = new InputSource(entry.openStream());
		Map<String, URLMapper> readURLs = URLMapperReader.readURLs(source);
		URLMapperCache.putURLMapper(readURLs);
		System.out.println("加载Action完成:" + readURLs.entrySet());
		
	}

	public void stop(BundleContext context) throws Exception {
		httpServiceTracker.close();
	}

	private class HttpServiceTracker extends ServiceTracker {

		public HttpServiceTracker(BundleContext context) {
			super(context, HttpService.class.getName(), null);
		}

		public Object addingService(ServiceReference reference) {
//			LogUtil.getLogger(this).debug("Service adding ....");
			System.out.println("Service adding ....");
			final HttpService httpService = (HttpService) context.getService(reference);
			try {
				HttpContext commonContext = new BundleEntryHttpContext(context.getBundle(), "/WebRoot"); 
				httpService.registerResources("/css", "/css", commonContext); 
				httpService.registerResources("/img", "/img", commonContext);
				httpService.registerResources("/js", "/js", commonContext);
				httpService.registerResources("/pages", "/pages", commonContext);

				String jspFolder = "/WebRoot/pages";
				
				String jspContext = "/jsp";
				
				Servlet adaptedJspServlet = new ContextPathServletAdaptor(new JspServlet(context.getBundle(), jspFolder), jspContext);  
				httpService.registerServlet(jspContext+"/*.jsp", adaptedJspServlet, null, commonContext); 
				
				Servlet testServlet = new TestServlet();
				httpService.registerServlet("/test.do",testServlet,null,commonContext);
				
				List<Filter> filters = new ArrayList<Filter>();
				filters.add(new CharacterFilter());
				
				Servlet controller = new ControllerServlet(filters);
				httpService.registerServlet(contextName+"/*.do",controller,null,commonContext);
			} catch (Exception e) {
//				LogUtil.getLogger(this).error("Bundle注册资源异常",e);
				e.printStackTrace();
			}
//			LogUtil.getLogger(this).debug("Service added!");
			System.out.println("Service added!");
			return httpService;
		}

		public void removedService(ServiceReference reference, Object service) {
			final HttpService httpService = (HttpService) service;
			httpService.unregister("/test.do"); 
			httpService.unregister("/pages");
			super.removedService(reference, service);
		}			
	}

}
