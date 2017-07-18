package org.smart4.framework.proxy;

/** 
* @ClassName: Proxy 
* @Description: 代理接口
* @author lj
* @date 2017年7月13日 下午3:35:43 
*  
*/
public interface Proxy {
	
	/** 
	* @Title: doProxy 
	* @Description: 执行链式代理
	* @param @param proxyChain
	* @param @return
	* @param @throws Throwable    
	* @return Object   
	* @throws 
	* @author
	*/
	Object doProxy(ProxyChain proxyChain) throws Throwable;

}
