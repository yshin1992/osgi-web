package org.huyue.coffee.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Action {

	public abstract void handleRequest(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException;
	
}
