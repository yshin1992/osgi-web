package org.huyue.coffee.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.huyue.coffee.Activator;

public class ControllerServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getRequestURI();
		
		//url路由
		if (Activator.contextName.equals(path)) {
			request.getRequestDispatcher("/pages/index.html").forward(request, response);
		} else {
			String actionName = path.substring(Activator.contextName.length());
			Action action = Activator.actionMap.get(actionName);
			
			if (action != null) {
				//对应action处理请求
				action.handleRequest(request, response);
			} else {
				request.getRequestDispatcher("/pages/error.html").forward(request, response);
			}
		}
	}
	
	

}
