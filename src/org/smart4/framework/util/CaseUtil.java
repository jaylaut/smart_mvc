package org.smart4.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @ClassName: CaseUtil 
* @Description: 转换类型工具
* @author 刘杰
* @date 2017年6月28日 上午8:48:40 
*  
*/
public final class CaseUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(CaseUtil.class);
	/** 
	* @Title: caseString 
	* @Description: 转为string
	* @param @param obj
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	public static String caseString(Object obj){
		return CaseUtil.caseString(obj,"");
	}
	
	/** 
	* @Title: caseString 
	* @Description: 转为String 可指定默认值
	* @param @param obj
	* @param @param defaultValue
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	public static String caseString(Object obj,String defaultValue){
		return obj != null ? String.valueOf(obj):defaultValue;
	}
	
	/** 
	* @Title: caseDouble 
	* @Description: 转为double 
	* @param @param obj
	* @param @return    
	* @return double   
	* @throws 
	* @author
	*/
	public static double caseDouble(Object obj){
		return CaseUtil.caseDouble(obj,0);
	}
	
	/** 
	* @Title: caseDouble 
	* @Description: 转为double(可指定默认值)
	* @param @param obj
	* @param @param defaultValue
	* @param @return    
	* @return double   
	* @throws 
	* @author
	*/
	public static double caseDouble(Object obj,double defaultValue){
		double doubleValue = defaultValue;
		if(obj != null){
			String doubleStr = caseString(doubleValue);
			if(StringUtil.isNotEmpty(doubleStr)){
				try{
					doubleValue = Double.parseDouble(doubleStr);
				}catch(NumberFormatException e){
					doubleValue = defaultValue;
					LOGGER.error("String to be formated double failure",e);
				}
				
			}
		}
		return doubleValue;
	}
	
	/** 
	* @Title: caseLong 
	* @Description: 转为long
	* @param @param obj
	* @param @return    
	* @return double   
	* @throws 
	* @author
	*/
	public static long caseLong(Object obj){
		return CaseUtil.caseLong(obj,0);
	}
	
	/** 
	* @Title: caseLong 
	* @Description: 转为long(可指定默认)
	* @param @param obj
	* @param @param defaultValue
	* @param @return    
	* @return long   
	* @throws 
	* @author
	*/
	public static long caseLong(Object obj,long defaultValue){
		long longValue = defaultValue;
		if(obj != null){
			String longStr = caseString(longValue);
			if(StringUtil.isNotEmpty(longStr)){
				try{
					longValue = Long.parseLong(longStr);
				}catch(NumberFormatException e){
					longValue = defaultValue;
					LOGGER.error("String to be formated long failure",e);
				}
				
			}
		}
		return longValue;
	}
	
	/** 
	* @Title: caseInt 
	* @Description: 转为int(默认是0)
	* @param @param obj
	* @param @return    
	* @return int   
	* @throws 
	* @author
	*/
	public static int caseInt(Object obj){
		return CaseUtil.caseInt(obj,0);
	}
	
	/** 
	* @Title: caseInt 
	* @Description: 转为int(可指定默认值)
	* @param @param obj
	* @param @param defaultValue
	* @param @return    
	* @return int   
	* @throws 
	* @author
	*/
	public static int caseInt(Object obj,int defaultValue){
		int intValue = defaultValue;
		if(obj != null){
			String longStr = caseString(intValue);
			if(StringUtil.isNotEmpty(longStr)){
				try{
					intValue = Integer.parseInt(longStr);
				}catch(NumberFormatException e){
					intValue = defaultValue;
					LOGGER.error("String to be formated long failure",e);
				}
				
			}
		}
		return intValue;
	}
	
	/** 
	* @Title: caseBoolean 
	* @Description: 转为boolean
	* @param @param obj
	* @param @return    
	* @return boolean   
	* @throws 
	* @author
	*/
	public static boolean caseBoolean(Object obj){
		return CaseUtil.caseBoolean(obj,false);
	}
	
	/** 
	* @Title: caseBoolean 
	* @Description: 转为boolean(可指定默认值)
	* @param @param obj
	* @param @param defaultVaule
	* @param @return    
	* @return boolean   
	* @throws 
	* @author
	*/
	public static boolean caseBoolean(Object obj,boolean defaultVaule){
		boolean booleanValue = defaultVaule;
		if(obj != null){
			booleanValue = Boolean.parseBoolean(caseString(obj));
		}
		return booleanValue;
	}
}
