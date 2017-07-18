package org.smart4.framework.aspect;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4.framework.annotation.Aspect;
import org.smart4.framework.annotation.Controller;
import org.smart4.framework.proxy.AspectProxy;

/** 
* @ClassName: ControllerAspect 
* @Description: 拦截controller所有方法
* @author lj
* @date 2017年7月14日 上午11:16:53 
*  
*/
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);
	
	private long begin;
	
	@Override
	public void before(Class<?> cls,Method method,Object[] params) throws Throwable{
		LOGGER.debug("------------------begin--------------------");
		LOGGER.debug(String.format("class : %s", cls.getName()));
		LOGGER.debug(String.format("method : %s", method.getName()));
		begin = System.currentTimeMillis();
	}
	
	@Override
	public void after(Class<?> cls,Method method,Object[] params,Object result) throws Throwable{
		LOGGER.debug(String.format("time : %dms", System.currentTimeMillis()-begin));
		LOGGER.debug("------------------------END----------------------");
	}

}
