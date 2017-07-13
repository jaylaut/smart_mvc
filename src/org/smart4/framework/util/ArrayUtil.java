package org.smart4.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/** 
* @ClassName: ArrayUtil 
* @Description: 数组工具类
* @author 刘杰
* @date 2017年7月12日 下午2:31:31 
*  
*/
public final class ArrayUtil {
	
	/** 
	* @Title: isNotEmpty 
	* @Description: 判断数组是否为非空
	* @param @param objs
	* @param @return    
	* @return boolean   
	* @throws 
	* @author
	*/
	public static boolean isNotEmpty(Object[] objs){
		return !ArrayUtils.isEmpty(objs);
	}
	
	/** 
	* @Title: isEmpty 
	* @Description: 判断数组是否为空
	* @param @param objs
	* @param @return    
	* @return boolean   
	* @throws 
	* @author
	*/
	public static boolean isEmpty(Object[] objs){
		return ArrayUtil.isEmpty(objs);
	}

}
