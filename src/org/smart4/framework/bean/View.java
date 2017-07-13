package org.smart4.framework.bean;

import java.util.HashMap;
import java.util.Map;

/** 
* @ClassName: View 
* @Description: 返回视图对象
* @author 刘杰
* @date 2017年7月12日 下午3:55:15 
*  
*/
public class View {

	/** 
	* @Fields path : 请求路径
	*/ 
	private String path;
	
	/** 
	* @Fields model : 数据模型 
	*/ 
	private Map<String,Object> model;
	
	public View(String path){
		this.path=path;
		model = new HashMap<String,Object>();
	}
	
	/** 
	* @Title: addModel 
	* @Description: 添加数据模型 
	* @param @param key
	* @param @param value
	* @param @return    
	* @return View   
	* @throws 
	* @author
	*/
	public View addModel(String key,Object value){
		model.put(key, value);
		return this;
	}
	
	public String getPath(){
		return path;
	}
	
	public Map<String,Object> getModel(){
		return model;
	}
}
