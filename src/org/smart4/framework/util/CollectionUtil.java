package org.smart4.framework.util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

/** 
* @ClassName: CollectionUtil 
* @Description: 集合工具类 
* @author 刘杰
* @date 2017年6月28日 上午9:50:45 
*  
*/
public final class CollectionUtil {

	/** 
	* @Title: isEmpty 
	* @Description: 判断集合是否为空
	* @param @param coll
	* @param @return    
	* @return boolean   
	* @throws 
	* @author
	*/
	public static boolean isEmpty(Collection<?> coll){
		return CollectionUtils.isEmpty(coll);
	}
	
	/** 
	* @Title: isNotEmpty 
	* @Description: 判断集合是否为非空 
	* @param @param coll
	* @param @return    
	* @return boolean   
	* @throws 
	* @author
	*/
	public static boolean isNotEmpty(Collection<?> coll){
		return !CollectionUtils.isEmpty(coll);
	}
	
	/** 
	* @Title: isEmpty 
	* @Description: 判断map是否为空
	* @param @param map
	* @param @return    
	* @return boolean   
	* @throws 
	* @author
	*/
	public static boolean isEmpty(Map<?,?>map){
		return MapUtils.isEmpty(map);
	}
	
	/** 
	* @Title: isNotEmpty 
	* @Description: 判断map是否为非空
	* @param @param map
	* @param @return    
	* @return boolean   
	* @throws 
	* @author
	*/
	public static boolean isNotEmpty(Map<?,?>map){
		return !MapUtils.isEmpty(map);
	}
}
