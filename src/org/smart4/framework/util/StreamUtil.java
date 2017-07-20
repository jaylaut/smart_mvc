package org.smart4.framework.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @ClassName: StreamUtil 
* @Description: 流工具类 
* @author lj
* @date 2017年7月13日 上午9:49:55 
*  
*/
public final class StreamUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);
	
	/** 
	* @Title: getString 
	* @Description: 从输入流获取字符串
	* @param @param is
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	public static String getString(InputStream is){
		StringBuilder sb = new StringBuilder();
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while((line = reader.readLine()) != null){
				sb.append(line);
			}
		}catch(Exception e){
			LOGGER.error("get String failure",e);
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
	
	/** 
	* @Title: copyStream 
	* @Description: 将输入流复制到输出流 
	* @param @param inputStream
	* @param @param outputStream    
	* @return void   
	* @throws 
	* @author
	*/
	public static void copyStream(InputStream inputStream,OutputStream outputStream){
		try{
			int length;
			byte[] buffer = new byte[4*1024];
			while((length=inputStream.read(buffer, 0, buffer.length))!= -1){
				outputStream.write(buffer, 0, length);
			}
			outputStream.flush();
		}catch(Exception e){
			LOGGER.error("copy file failure",e);
			throw new RuntimeException(e);
		}finally{
			try{
				inputStream.close();
				outputStream.close();
			}catch(Exception e){
				LOGGER.error("close stream failure",e);
			}
		}
	}
}
