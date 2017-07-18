package org.smart4.framework.help;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.smart4.framework.util.ReflectionUtil;

/** 
* @ClassName: BeanHelper 
* @Description: bean工具类 
* @author 刘杰
* @date 2017年7月12日 上午11:00:13 
*  
*/
public final class BeanHelper {

	
	/** 
	* @Fields BEAN_MAP : 定义bean映射，用于存放Bean类与bean实体的映射关系 
	*/ 
	private static final Map<Class<?>,Object> BEAN_MAP =  new HashMap<Class<?>,Object>();
	
	static{
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		for(Class<?> cls : beanClassSet){
			Object obj = ReflectionUtil.newInstance(cls);
			BEAN_MAP.put(cls, obj);
		}
	}
	
	/** 
	* @Title: getBeanMap 
	* @Description: 获取bean映射
	* @param @return    
	* @return Map<Class<?>,Object>   
	* @throws 
	* @author
	*/
	public static Map<Class<?>,Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	/** 
	* @Title: setBean 
	* @Description: 设置bean
	* @param @param cls
	* @param @param obj    
	* @return void   
	* @throws 
	* @author
	*/
	public static void setBean(Class<?> cls,Object obj){
		BEAN_MAP.put(cls, obj);
	}
	
	/** 
	* @Title: getBean 
	* @Description: 获取bean实例 
	* @param @param cls
	* @param @return    
	* @return T   
	* @throws 
	* @author
	*/
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> cls){
		if(!BEAN_MAP.containsKey(cls)){
			throw new RuntimeException("can not get bean by class" + cls);
		}
		return (T) BEAN_MAP.get(cls);
	}
}
