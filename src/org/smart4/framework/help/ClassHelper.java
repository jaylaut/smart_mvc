package org.smart4.framework.help;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import org.smart4.framework.annotation.Controller;
import org.smart4.framework.annotation.Service;
import org.smart4.framework.util.ClassUtil;


/** 
* @ClassName: ClassHelper 
* @Description:class工具类 
* @author 刘杰
* @date 2017年7月12日 下午2:30:50 
*  
*/
public final class ClassHelper {
	
	private static final Set<Class<?>> CLASS_SET;
	
	static{
		String basePackage = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}
	
	/** 
	* @Title: getClassSet 
	* @Description: 获取应用包名下所有的类
	* @param @return    
	* @return Set<Class<?>>   
	* @throws 
	* @author
	*/
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	
	/** 
	* @Title: getServiceClassSet 
	* @Description: 获取所有注解为service的类
	* @param @return    
	* @return Set<Class<?>>   
	* @throws 
	* @author
	*/
	public static Set<Class<?>> getServiceClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls : CLASS_SET){
			if(cls.isAnnotationPresent(Service.class)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/** 
	* @Title: getControllerClassSet 
	* @Description: 获取所有注解为controller的类 
	* @param @return    
	* @return Set<Class<?>>   
	* @throws 
	* @author
	*/
	public static Set<Class<?>> getControllerClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls : CLASS_SET){
			if(cls.isAnnotationPresent(Controller.class)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/** 
	* @Title: getBeanClassSet 
	* @Description:获取所有bean类(包括service，controller)
	* @param @return    
	* @return Set<Class<?>>   
	* @throws 
	* @author
	*/
	public static Set<Class<?>> getBeanClassSet(){
		Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
		beanClassSet.addAll(getControllerClassSet());
		beanClassSet.addAll(getServiceClassSet());
		return beanClassSet;
	}

}
