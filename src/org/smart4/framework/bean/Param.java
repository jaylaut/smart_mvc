package org.smart4.framework.bean;

import java.util.Map;

import org.smart4.framework.util.CaseUtil;
import org.smart4.framework.util.CollectionUtil;

/** 
* @ClassName: Param 
* @Description: 请求参数对象 
* @author 刘杰
* @date 2017年7月12日 下午3:44:03 
*  
*/
public class Param {

	private Map<String,Object> paramMap;

	public Param(Map<String, Object> paramMap) {
		super();
		this.paramMap = paramMap;
	}
	
	/** 
	* @Title: getLong 
	* @Description: 根据参数名获取long参数值
	* @param @param name
	* @param @return    
	* @return long   
	* @throws 
	* @author
	*/
	public long getLong(String name){
		return CaseUtil.caseLong(paramMap.get(name));
	}
	
	/** 
	* @Title: getMap 
	* @Description: 获取所有字段信息
	* @param @return    
	* @return Map<String,Object>   
	* @throws 
	* @author
	*/
	public Map<String,Object> getMap(){
		return paramMap;
	}
	
	/** 
	* @Title: isEmpty 
	* @Description: 校验参数是否为空
	* @param @return    
	* @return boolean   
	* @throws 
	* @author
	*/
	public boolean isEmpty(){
		return CollectionUtil.isEmpty(paramMap);
	}
}
