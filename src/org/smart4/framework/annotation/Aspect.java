package org.smart4.framework.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/** 
* @ClassName: Aspect 
* @Description:切面注解 
* @author lj
* @date 2017年7月13日 下午3:32:56 
*  
*/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
	
	Class<? extends Annotation> value();

}
