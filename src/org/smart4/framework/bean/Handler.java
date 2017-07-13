package org.smart4.framework.bean;

import java.lang.reflect.Method;

/** 
* @ClassName: Handler 
* @Description: 封装action信息
* @author 刘杰
* @date 2017年7月12日 下午2:30:10 
*  
*/
public class Handler {

	/** 
	* @Fields controllerClass : Controller类
	*/ 
	private Class<?> controllerClass;
	
	/** 
	* @Fields actionMethod : action方法 
	*/ 
	private Method actionMethod;

	public Handler(Class<?> controllerClass, Method actionMethod) {
		super();
		this.controllerClass = controllerClass;
		this.actionMethod = actionMethod;
	}

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public Method getActionMethod() {
		return actionMethod;
	}
	
	
}
