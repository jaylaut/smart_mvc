package org.smart4.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

	/** 
	* @Title: value 
	* @Description: 请求类型与路径
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	String value();
}
