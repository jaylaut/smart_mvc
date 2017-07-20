package org.smart4.framework.util;

import org.apache.commons.lang3.StringUtils;

/** 
* @ClassName: StringUtil 
* @Description: 字符串工具类
* @author 刘杰
* @date 2017年6月28日 上午8:49:04 
*  
*/
public final class StringUtil {
	
	public static final String SEPARATOR = String.valueOf((char)29);

	/** 
	* @Title: isEmpty 
	* @Description: 判断字符串是否为空
	* @param @param str
	* @param @return    
	* @return boolean   
	* @throws 
	* @author
	*/
	public static boolean isEmpty(String str){
		if(str != null){
			str = str.trim();
		}
		return StringUtils.isEmpty(str);
	}
	
	/** 
	* @Title: isNotEmpty 
	* @Description: 判断是否为非空 
	* @param @param str
	* @param @return    
	* @return boolean   
	* @throws 
	* @author
	*/
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
}
