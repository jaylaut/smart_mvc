package org.smart4.framework.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @ClassName: PropsUtil 
* @Description: 读取属性文件工具
* @author 刘杰
* @date 2017年6月28日 上午8:48:23 
*  
*/
public class PropsUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);
	
	/** 
	* @Title: loadProps 
	* @Description: 加载属性文件
	* @param @param fileName
	* @param @return    
	* @return Properties   
	* @throws 
	* @author
	*/
	public static Properties loadProps(String fileName){
		Properties props = null;
		InputStream is = null;
		try{
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if(is == null){
				throw new FileNotFoundException(fileName + "file is not found");
			}	
			props = new Properties();
			props.load(is);
		}catch(IOException e){
			LOGGER.error("load properties file failure",e);
		}finally{
			if(is != null){
				try{
					is.close();
				}catch(IOException e){
					LOGGER.error("close input stream failure",e);
				}
				
			}
		}
		return props;
	}
	
	/** 
	* @Title: getString 
	* @Description: 获取字符型属性(默认为空字符)
	* @param @param pros
	* @param @param key
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	public static String getString(Properties pros,String key){
		return getString(pros,key,"");
	}
	
	/** 
	* @Title: getString 
	* @Description: 获取字符型属性(可指定默认值)
	* @param @param pros
	* @param @param key
	* @param @param defaultValue
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	public static String getString(Properties pros,String key,String defaultValue){
		String value = defaultValue;
		if(pros.containsKey(key)){
			value = pros.getProperty(key);
		}
		return value;
	}
	
	/** 
	* @Title: getInt 
	* @Description: 获取int类型属性(默认是0) 
	* @param @param pros
	* @param @param key
	* @param @return    
	* @return int   
	* @throws 
	* @author
	*/
	public static int getInt(Properties pros,String key){
		return getInt(pros,key,0);
	}
	
	/** 
	* @Title: getInt 
	* @Description: 获取int类型属性(可指定默认值) 
	* @param @param pros
	* @param @param key
	* @param @param defaultValue
	* @param @return    
	* @return int   
	* @throws 
	* @author
	*/
	public static int getInt(Properties pros,String key,int defaultValue){
		int value = defaultValue;
		if(pros.containsKey(key)){
			value = CaseUtil.caseInt(pros.getProperty(key));
		}
		return value;
	}
	
	/** 
	* @Title: getBoolean 
	* @Description: 获取boolean类型属性(默认为false) 
	* @param @param pros
	* @param @param key
	* @param @return    
	* @return boolean   
	* @throws 
	* @author
	*/
	public static boolean getBoolean(Properties pros,String key){
		return getBoolean(pros,key,false);
	}
	
	/** 
	* @Title: getBoolean 
	* @Description: 获取boolean类型属性(可指定)
	* @param @param pros
	* @param @param key
	* @param @param defaultValue
	* @param @return    
	* @return boolean   
	* @throws 
	* @author
	*/
	public static boolean getBoolean(Properties pros,String key,boolean defaultValue){
		boolean value = defaultValue;
		if(pros.containsKey(key)){
			value = CaseUtil.caseBoolean(pros.getProperty(key));
		}
		return value;
	}
}
