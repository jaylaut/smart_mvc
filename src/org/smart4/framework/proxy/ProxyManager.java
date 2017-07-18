package org.smart4.framework.proxy;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @ClassName: ProxyManager
 * @Description:代理管理类
 * @author lj
 * @date 2017年7月14日 上午10:07:03
 * 
 */
public class ProxyManager {

	@SuppressWarnings("unchecked")
	public static <T> T createProxy(final Class<?> targetClass,final List<Proxy> proxyList){
		
		return (T)Enhancer.create(targetClass,new MethodInterceptor(){
			
			@Override
			public Object intercept(Object targetObject,Method targetMethod,Object[] methodParams,MethodProxy methodProxy) throws Throwable {
				return new ProxyChain(targetClass, targetObject, targetMethod, methodProxy, methodParams, proxyList).doProxyChain();
			}
		});
		
	}
}
