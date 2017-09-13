package org.huyue.coffee.sys;

/**
 * URL映射值对象
 * @author yshin1992
 *
 */
public class URLMapper {

	private String id;
	
	private String clazz;
	
	private String method;


	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "URLMapper [id=" + id + ", clazz=" + clazz + ", method=" + method + "]";
	}

	
}
