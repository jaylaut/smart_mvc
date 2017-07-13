package org.smart4.framework.help;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.smart4.framework.annotation.Action;
import org.smart4.framework.bean.Handler;
import org.smart4.framework.bean.Request;
import org.smart4.framework.util.ArrayUtil;
import org.smart4.framework.util.CollectionUtil;


/** 
* @ClassName: ControllerHelper 
* @Description: controller工具类 
* @author 刘杰
* @date 2017年7月12日 下午2:29:29 
*  
*/
public final class ControllerHelper {

	/** 
	* @Fields ACTION_MAP : 用户存放请求与处理器之间的映射(action map) 
	*/ 
	private static final Map<Request,Handler> ACTION_MAP = new HashMap<Request,Handler>();
	
	static{
		//获取所有controller类
		Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
		if(CollectionUtil.isNotEmpty(controllerClassSet)){
			//遍历controller类
			for(Class<?> controllerClass : controllerClassSet){
				//获取controller中的方法
				Method[] methods = controllerClass.getDeclaredMethods();
				if(ArrayUtil.isNotEmpty(methods)){
					//遍历方法
					for(Method method : methods){
						//判断当前方法是否有Action注解
						if(method.isAnnotationPresent(Action.class)){
							//从action获取注解url的映射规则
							Action action = method.getAnnotation(Action.class);
							String mapping = action.value();
							//验证url映射规则
							if(mapping.matches("\\w+:/\\w*")){
								String [] array = mapping.split(":");
								if(ArrayUtil.isNotEmpty(array) && array.length==2){
									//获取请求方法和请求路径
									String requestMethod = array[0];
									//获取请求路径
									String requestPath = array[1];
									Request request = new Request(requestMethod,requestPath);
									Handler handler = new Handler(controllerClass,method);
									//初始化action map
									ACTION_MAP.put(request, handler);
								}
							}
						}
					}
				}
			}
		}
	}
	
	/** 
	* @Title: getHandler 
	* @Description: 获取Handler
	* @param @param requestMethod
	* @param @param requestPath
	* @param @return    
	* @return Handler   
	* @throws 
	* @author
	*/
	public static Handler getHandler(String requestMethod,String requestPath){
		Request request = new Request(requestMethod,requestPath);
		return ACTION_MAP.get(request);
	}
}
