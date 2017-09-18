package org.huyue.coffee.sys.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Log4j工具类
 * @author yshin1992
 *
 */
public class LogUtil {
	
	private static final Map<String,Logger> LOG_MAP = new HashMap<String,Logger>();
	
	public static Logger getLogger(Object obj){
		if(!LOG_MAP.containsKey(obj.getClass().getName())){
			LOG_MAP.put(obj.getClass().getName(), Logger.getLogger(obj.getClass()));
		}
		return LOG_MAP.get(obj.getClass().getName());
	}
	
	public static Logger getLogger(Class<?> clazz){
		if(!LOG_MAP.containsKey(clazz.getName())){
			LOG_MAP.put(clazz.getName(), Logger.getLogger(clazz));
		}
		return LOG_MAP.get(clazz.getName());
	}
	
}
