package org.smart4.framework.proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.proxy.MethodProxy;

/** 
* @ClassName: ProxyChain 
* @Description: 代理链 
* @author lj
* @date 2017年7月13日 下午3:38:00 
*  
*/
public class ProxyChain {
	
	
	/** 
	* @Fields targetClass : 目标类
	*/ 
	private final Class<?> targetClass;
	
	/** 
	* @Fields targetObject : 目标对象
	*/ 
	private final Object targetObject;
	
	/** 
	* @Fields targetMethod : 目标方法
	*/ 
	private final Method targetMethod;
	
	/** 
	* @Fields methodProxy : 代理方法
	*/ 
	private final MethodProxy methodProxy;
	
	/** 
	* @Fields methodParams : 参数列表
	*/ 
	private final Object[] methodParams;
	
	/** 
	* @Fields proxyList : 代理列表 
	*/ 
	private List<Proxy> proxyList = new ArrayList<Proxy>();
	
	/** 
	* @Fields proxyIndex : 代理索引
	*/ 
	private int proxyIndex = 0;

	public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy,
			Object[] methodParams, List<Proxy> proxyList) {
		super();
		this.targetClass = targetClass;
		this.targetObject = targetObject;
		this.targetMethod = targetMethod;
		this.methodProxy = methodProxy;
		this.methodParams = methodParams;
		this.proxyList = proxyList;
	}

	public List<Proxy> getProxyList() {
		return proxyList;
	}

	public void setProxyList(List<Proxy> proxyList) {
		this.proxyList = proxyList;
	}

	public int getProxyIndex() {
		return proxyIndex;
	}

	public void setProxyIndex(int proxyIndex) {
		this.proxyIndex = proxyIndex;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public Object getTargetObject() {
		return targetObject;
	}

	public Method getTargetMethod() {
		return targetMethod;
	}

	public MethodProxy getMethodProxy() {
		return methodProxy;
	}

	public Object[] getMethodParams() {
		return methodParams;
	}
	
	public Object doProxyChain() throws Throwable{
		Object methodResult;
		if(proxyIndex < proxyList.size()){
			methodResult = proxyList.get(proxyIndex++).doProxy(this);
		}else{
			methodResult = methodProxy.invoke(targetObject, methodParams);
		}
		return methodResult;
	}

}
