package org.huyue.coffee.controller;

import org.huyue.coffee.sys.controller.Action;

public class HelloAction extends Action {

	public void hello(){
		addAttr("username", "Guest");
		forward("/jsp/hello.jsp");
	}
	
	public void say(){
		addAttr("username", "HomeLand");
		forward("/jsp/hello.jsp");
	}
	
	public void done(){
		addAttr("username", "Sana");
		forward("/jsp/hello.jsp");
	}
	
}
