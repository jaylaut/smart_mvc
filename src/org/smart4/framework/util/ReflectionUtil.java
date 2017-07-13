package org.smart4.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @ClassName: ReflectionUtil 
* @Description: 反射工具类
* @author 刘杰
* @date 2017年7月12日 下午2:31:58 
*  
*/
public final class ReflectionUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);
	
	/** 
	* @Title: newInstance 
	* @Description: 创建实例 
	* @param @param cls
	* @param @return    
	* @return Object   
	* @throws 
	* @author
	*/
	public static Object newInstance(Class<?> cls){
		Object instance;
		try{
			instance = cls.newInstance();
		}catch(Exception e){
			LOGGER.error("new instance failure",e);
			throw new RuntimeException(e);
		}
		return instance;
	}
	
	/** 
	* @Title: invokeMethod 
	* @Description: 调用方法
	* @param @param obj
	* @param @param method
	* @param @param args
	* @param @return    
	* @return Object   
	* @throws 
	* @author
	*/
	public static Object invokeMethod(Object obj,Method method,Object... args){
		Object result;
		try{
			method.setAccessible(true);
			result = method.invoke(obj, args);
		}catch(Exception e){
			LOGGER.error("invoke method failure",e);
			throw new RuntimeException(e);
		}
		return result;
	}
	
	/** 
	* @Title: setField 
	* @Description: 设置成员变量的值
	* @param @param obj
	* @param @param field
	* @param @param value    
	* @return void   
	* @throws 
	* @author
	*/
	public static void setField(Object obj,Field field,Object value){
		try{
			field.setAccessible(true);
			field.set(obj, value);
		}catch(Exception e){
			LOGGER.error("set field failure",e);
			throw new RuntimeException(e);
		}
	}

}
