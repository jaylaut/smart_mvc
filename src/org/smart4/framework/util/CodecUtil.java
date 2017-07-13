package org.smart4.framework.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @ClassName: CodecUtil 
* @Description: 编码、解码工具
* @author acer
* @date 2017年7月13日 上午11:35:21 
*  
*/
public final class CodecUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);
	
	/** 
	* @Title: encodeURL 
	* @Description: 把url编码
	* @param @param source
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	public static String encodeURL(String source){
		String target;
		try{
			target = URLEncoder.encode(source, "UTF-8");
		}catch(Exception e){
			LOGGER.error("encode url failure",e);
			throw new RuntimeException(e);
		}
		return target;
	}
	
	/** 
	* @Title: decodeURL 
	* @Description: 把url解码
	* @param @param source
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	public static String decodeURL(String source){
		String target;
		try{
			target = URLDecoder.decode(source,"UTF-8");
		}catch(Exception e){
			LOGGER.error("decode url failure",e);
			throw new RuntimeException(e);
		}
		return target;
	}

}
