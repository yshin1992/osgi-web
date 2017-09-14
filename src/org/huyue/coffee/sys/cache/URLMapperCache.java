package org.huyue.coffee.sys.cache;

import java.util.HashMap;
import java.util.Map;

import org.huyue.coffee.sys.URLMapper;

/**
 * 用于缓存URLMapper
 * @author yshin1992
 *
 */
public class URLMapperCache {
	
	private static Map<String,URLMapper> urlMapper = new HashMap<String,URLMapper>();
	
	public static void putURLMapper(Map<String,URLMapper> mapper){
		urlMapper.putAll(mapper);
	}
	
	public static URLMapper getURLMapper(String mapperId){
		return urlMapper.get(mapperId);
	}
}
