package org.huyue.coffee.sys.util;

/**
 * 用于判别url是否匹配
 * @author yshin1992
 *
 */
public class UrlPatternUtil {
	
	public static boolean pattern(String regex,String charSeq){
		//首先判定是不是有 * 模糊匹配的字符
		//没有的情况下，就是严格匹配
		int index = regex.indexOf("*");
		if(index == -1){
			return regex.equals(charSeq);
		}else{
			//有*模糊匹配字符的情况下，将*匹配字符前的字符串进行匹配，之后再对后缀进行匹配
			if(!regex.substring(0, index).equals(charSeq.substring(0,index))){
				return false;
			}else{
				//判断*后面是不是有其他字符
				//有其他字的情况下，对其他字进行匹配
				//没有，则认为匹配通过
				if(index == regex.length()-1){
					return true;
				}else{
					String end = regex.substring(index+1);
					if(charSeq.endsWith(end)){
						return true;
					}else{
						return false;
					}
				}
			}
		}
	}
}
