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

	public URLMapper(){
		
	}
	
	/**
	 * 初始化构造函数
	 * @param id Action唯一标识
	 * @param clazz 包含包名的Action类名称
	 * @param method 方法
	 */
	public URLMapper(String id,String clazz,String method){
		this.id = id;
		this.clazz = clazz;
		this.method = method;
	}

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
