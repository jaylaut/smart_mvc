package org.smart4.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.smart4.framework.bean.Data;
import org.smart4.framework.bean.Handler;
import org.smart4.framework.bean.Param;
import org.smart4.framework.bean.View;
import org.smart4.framework.help.BeanHelper;
import org.smart4.framework.help.ConfigHelper;
import org.smart4.framework.help.ControllerHelper;
import org.smart4.framework.util.ArrayUtil;
import org.smart4.framework.util.CodecUtil;
import org.smart4.framework.util.JsonUtil;
import org.smart4.framework.util.ReflectionUtil;
import org.smart4.framework.util.StreamUtil;
import org.smart4.framework.util.StringUtil;

/** 
* @ClassName: DispatcherServlet 
* @Description: 请求转发器 
* @author acer
* @date 2017年7月12日 下午4:54:26 
*  
*/
@WebServlet(urlPatterns = "/*",loadOnStartup=0)
public class DispatcherServlet extends HttpServlet{
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException{
		//初始化相关类
		HelperLoader.init();
		//获取ServletContext对象，用于注册servlet
		ServletContext servletContext = servletConfig.getServletContext();
		//注册处理jsp的servlet
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
		//注册处理静态资源的默认servlet
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");
	}
	
	/* (非 Javadoc) 
	* <p>Title: service</p> 
	* <p>Description: </p> 
	* @param request
	* @param response
	* @throws ServletException
	* @throws IOException 
	* @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse) 
	*/
	@Override
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		
		String requestMethod = request.getMethod().toLowerCase();
		String requestPath = request.getPathInfo();
		//获取action处理器
		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
		if(handler != null){
			//获取controller类及bean
			Class<?> controllerClass = handler.getControllerClass();
			Object controllerBean = BeanHelper.getBean(controllerClass);
			//创建请求参数
			Map<String,Object> paramMap = new HashMap<String,Object>();
			Enumeration<String> paramNames = request.getParameterNames();
			while(paramNames.hasMoreElements()){
				String paramName = paramNames.nextElement();
				String paramValue = request.getParameter(paramName);
				paramMap.put(paramName, paramValue);
			}
			String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
			if(StringUtil.isNotEmpty(body)){
				String[] params = body.split("&");
				if(ArrayUtil.isNotEmpty(params)){
					for(String param : params){
						String [] array = param.split("=");
						if(ArrayUtil.isNotEmpty(array)&&array.length==2){
							String paramName = array[0];
							String paramValue = array[1];
							paramMap.put(paramName, paramValue);
						}
					}
				}
			}
			Param param = new Param(paramMap);
			//调用Action方法
			Method method = handler.getActionMethod();
			Object result;
			if(param.isEmpty()){
				result = ReflectionUtil.invokeMethod(controllerBean, method);
			}else{
				result = ReflectionUtil.invokeMethod(controllerBean, method, param);
			}
			
			//处理Action返回值
			if(result instanceof View){
				View view = (View) result;
				String path = view.getPath();
				if(StringUtil.isNotEmpty(path)){
					if(path.startsWith("/")){
						response.sendRedirect(request.getContextPath()+path);
					}else{
						Map<String,Object> model = view.getModel();
						for(Map.Entry<String,Object> entry : model.entrySet()){
							request.setAttribute(entry.getKey(), entry.getValue());
						}
						request.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(request, response);
					}
				}
			}else if(result instanceof Data){
				//返回json数据
				Data data = (Data)result;
				Object model = data.getModel();
				if(model != null){
					response.setContentType("applicaiton/json");
					response.setCharacterEncoding("UTF-8");
					PrintWriter writer = response.getWriter();
					String json = JsonUtil.toJson(model);
					writer.write(json);
					writer.flush();
					writer.close();
				}
			}
			
		}
	}

}
