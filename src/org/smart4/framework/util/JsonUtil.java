package org.smart4.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/** 
* @ClassName: JsonUtil 
* @Description: json工具类 
* @author lj
* @date 2017年7月13日 下午2:06:22 
*  
*/
public final class JsonUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
	
	private static final ObjectMapper  OBJECT_MAPPER = new ObjectMapper();
	
	/** 
	* @Title: toJson 
	* @Description: 把pojo转为json
	* @param @param obj
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	public static <T> String toJson(T obj){
		String json;
		try{
			json = OBJECT_MAPPER.writeValueAsString(obj);
		}catch(Exception e){
			LOGGER.error("convert object to json failure",e);
			throw new RuntimeException(e);
		}
		return json;
	}
	
	/** 
	* @Title: fromJson 
	* @Description: 把json转为pojo对象 
	* @param @param json
	* @param @param type
	* @param @return    
	* @return T   
	* @throws 
	* @author
	*/
	public static <T> T fromJson(String json,Class<T> type){
		T pojo;
		try{
			pojo = OBJECT_MAPPER.readValue(json, type);
		}catch(Exception e){
			LOGGER.error("convert json to object failure",e);
			throw new RuntimeException(e);
		}
		return pojo;
	}
}
