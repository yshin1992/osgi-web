package org.huyue.coffee.controller;

import java.io.IOException;

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

	public void prepare(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
		
	}
	
	public void confirm(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
		
	}
	
	public void submit(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
		
	}
	
}
