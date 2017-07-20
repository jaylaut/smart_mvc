package org.smart4.framework.help;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServletHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServletHelper.class);
	
	/** 
	* @Fields SERVLET_HELPER_HOLDER : 把每一个ServletHelper实例放到线程池里
	*/ 
	private static final ThreadLocal<ServletHelper> SERVLET_HELPER_HOLDER = new ThreadLocal<ServletHelper>();
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private ServletHelper(HttpServletRequest request,HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	/** 
	* @Title: init 
	* @Description: 初始化
	* @param @param request
	* @param @param response    
	* @return void   
	* @throws 
	* @author
	*/
	public static void init(HttpServletRequest request,HttpServletResponse response){
		SERVLET_HELPER_HOLDER.set(new ServletHelper(request,response));
	}
	
	/** 
	* @Title: remove 
	* @Description: 销毁
	* @param     
	* @return void   
	* @throws 
	* @author
	*/
	public static void destory(){
		SERVLET_HELPER_HOLDER.remove();
	}
	
	/** 
	* @Title: getRequest 
	* @Description: 获取request对象 
	* @param @return    
	* @return HttpServletRequest   
	* @throws 
	* @author
	*/
	private static HttpServletRequest getRequest(){
		return SERVLET_HELPER_HOLDER.get().request;
	}

	/** 
	* @Title: getResponse 
	* @Description: 获取response对象
	* @param @return    
	* @return HttpServletResponse   
	* @throws 
	* @author
	*/
	private static HttpServletResponse getResponse(){
		return SERVLET_HELPER_HOLDER.get().response;
	}
	
	/** 
	* @Title: getSession 
	* @Description: 获取session
	* @param @return    
	* @return HttpSession   
	* @throws 
	* @author
	*/
	private static HttpSession getSession(){
		return getRequest().getSession();
	}
	
	/** 
	* @Title: getServletContext 
	* @Description: 获取ServletContext
	* @param @return    
	* @return ServletContext   
	* @throws 
	* @author
	*/
	private static ServletContext getServletContext(){
		return getRequest().getServletContext();
	}
	
	/** 
	* @Title: setRequestAttribute 
	* @Description: 把属性放到request中
	* @param @param key
	* @param @param value    
	* @return void   
	* @throws 
	* @author
	*/
	public static void setRequestAttribute(String key,Object value){
		getRequest().setAttribute(key, value);
	}
	
	/** 
	* @Title: getRequestAttribute 
	* @Description: 从request获取属性
	* @param @param key
	* @param @return    
	* @return T   
	* @throws 
	* @author
	*/
	@SuppressWarnings("unchecked")
	public static <T> T getRequestAttribute(String key){
		return (T) getRequest().getAttribute(key);
	}
	
	/** 
	* @Title: remoteRequestAttribute 
	* @Description: 从request移除属性
	* @param @param key    
	* @return void   
	* @throws 
	* @author
	*/
	public static void remoteRequestAttribute(String key){
		getRequest().removeAttribute(key);
	}
	
	/** 
	* @Title: sendRedirect 
	* @Description: 发送重定向响应
	* @param @param location    
	* @return void   
	* @throws 
	* @author
	*/
	public static void sendRedirect(String location){
		try{
			getResponse().sendRedirect(getRequest().getContextPath()+location);
		}catch(Exception e){
			LOGGER.error("redirect failure",e);
		}
	}
	
	/** 
	* @Title: setSessionAttribute 
	* @Description: 把属性放到session中
	* @param @param key
	* @param @param value    
	* @return void   
	* @throws 
	* @author
	*/
	public static void setSessionAttribute(String key,Object value){
		getSession().setAttribute(key, value);
	}
	
	
}
