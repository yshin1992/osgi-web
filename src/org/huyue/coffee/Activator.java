package org.huyue.coffee;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;

import org.eclipse.equinox.http.helper.BundleEntryHttpContext;
import org.eclipse.equinox.http.helper.ContextPathServletAdaptor;
import org.eclipse.equinox.jsp.jasper.JspServlet;
import org.huyue.coffee.controller.Action;
import org.huyue.coffee.controller.ControllerServlet;
import org.huyue.coffee.controller.HelloAction;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

	private static BundleContext context;

	public static String contextName = "/coffee";
	
	public static Map<String, Action> actionMap = new HashMap<String, Action>();
	
	static {
		actionMap.put("/hello.do", new HelloAction());
	}
	
	static BundleContext getContext() {
		return context;
	}

	private ServiceTracker httpServiceTracker;

	public void start(BundleContext context) throws Exception {
		httpServiceTracker = new HttpServiceTracker(context);
		httpServiceTracker.open();
	}

	public void stop(BundleContext context) throws Exception {
		httpServiceTracker.close();
	}

	private class HttpServiceTracker extends ServiceTracker {

		public HttpServiceTracker(BundleContext context) {
			super(context, HttpService.class.getName(), null);
		}

		public Object addingService(ServiceReference reference) {
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
				
				Servlet controller = new ControllerServlet();
				httpService.registerServlet(contextName+"/*.do",controller,null,commonContext);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
