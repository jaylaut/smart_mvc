package org.smart4.framework.help;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4.framework.annotation.Aspect;
import org.smart4.framework.annotation.Service;
import org.smart4.framework.proxy.AspectProxy;
import org.smart4.framework.proxy.Proxy;
import org.smart4.framework.proxy.ProxyManager;
import org.smart4.framework.proxy.TransactionProxy;

/** 
* @ClassName: AopHelper 
* @Description: 方法拦截助手
* @author liujie
* @date 2017年7月18日 下午2:17:03 
*  
*/

public final class AopHelper {
	
	private static Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);
	
	//加载aop
	static{
		try{
			Map<Class<?>,Set<Class<?>>> proxyMap = createProxyMap();
			Map<Class<?>,List<Proxy>> targetMap = createTargetMap(proxyMap);
			for(Map.Entry<Class<?>,List<Proxy>> targetEntry:targetMap.entrySet()){
				Class<?> targetClass = targetEntry.getKey();
				List<Proxy> proxyList = targetEntry.getValue();
				Object proxy = ProxyManager.createProxy(targetClass, proxyList);
				BeanHelper.setBean(targetClass, proxy);
			}
		}catch(Exception e){
			LOGGER.error("aop failure",e);
		}
	}
	
	private static Set<Class<?>> createTargerClassSet(Aspect aspect) throws Exception{
		Set<Class<?>> targetClass = new HashSet<Class<?>>();
		Class<? extends Annotation> annotation = aspect.value();
		if(annotation != null && !annotation.equals(Aspect.class)){
			targetClass.addAll(ClassHelper.getClassSetByAnnotation(annotation));
		}
		return targetClass;
	}
	
	/** 
	* @Title: createProxyMap 
	* @Description: 获取代理类及其目标类的映射关系
	* @param @return
	* @param @throws Exception    
	* @return Map<Class<?>,Set<Class<?>>>   
	* @throws 
	* @author
	*/
	private static Map<Class<?>,Set<Class<?>>> createProxyMap() throws Exception{
		Map<Class<?>,Set<Class<?>>> proxyMap = new HashMap<Class<?>,Set<Class<?>>>();
		Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
		for(Class<?> proxyClass : proxyClassSet){
			if(proxyClass.isAnnotationPresent(Aspect.class)){
				Aspect aspect = proxyClass.getAnnotation(Aspect.class);
				Set<Class<?>> targetClassSet = createTargerClassSet(aspect);
				proxyMap.put(proxyClass, targetClassSet);
			}
		}
		return proxyMap;
	}
	
	/** 
	* @Title: createTargetMap 
	* @Description: 根据代理类及其目标类的映射关系，分析目标类与代理对象的映射关系 
	* @param @param proxyMap
	* @param @return
	* @param @throws Exception    
	* @return Map<Class<?>,List<Proxy>>   
	* @throws 
	* @author
	*/
	private static Map<Class<?>,List<Proxy>> createTargetMap(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception{
		Map<Class<?>,List<Proxy>> targetMap = new HashMap<Class<?>,List<Proxy>>();
		for(Map.Entry<Class<?>,Set<Class<?>>> proxyEntry:proxyMap.entrySet()){
			Class<?> proxyClass = proxyEntry.getKey();
			Set<Class<?>> targetClassSet = proxyEntry.getValue();
			for(Class<?> targetClass : targetClassSet){
				Proxy proxy = (Proxy) proxyClass.newInstance();
				if(targetMap.containsKey(targetClass)){
					targetMap.get(targetClass).add(proxy);
				}else{
					List<Proxy> proxyList = new ArrayList<Proxy>();
					proxyList.add(proxy);
					targetMap.put(targetClass, proxyList);
				}
			}
		}
		return targetMap;
	}
	
	/** 
	* @Title: addAspectProxy 
	* @Description: 添加切面代理类
	* @param @param proxyMap
	* @param @throws Exception    
	* @return void   
	* @throws 
	* @author
	*/
	private static void addAspectProxy(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception{
		Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
		for(Class<?> proxyClass:proxyClassSet){
			if(proxyClass.isAnnotationPresent(Aspect.class)){
				Aspect aspect = proxyClass.getAnnotation(Aspect.class);
				Set<Class<?>> targetClassSet = createTargerClassSet(aspect);
				proxyMap.put(proxyClass, targetClassSet);
			}
		}
	}
	
	/** 
	* @Title: addTransactionProxy 
	* @Description: 添加事务代理类
	* @param @param proxyMap
	* @param @throws Exception    
	* @return void   
	* @throws 
	* @author
	*/
	private static void addTransactionProxy(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception{
		Set<Class<?>> serviceClassSet = ClassHelper.getClassSetByAnnotation(Service.class);
		proxyMap.put(TransactionProxy.class, serviceClassSet);
		
	}

}
