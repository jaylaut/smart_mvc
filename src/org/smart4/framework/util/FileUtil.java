package org.smart4.framework.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @ClassName: FileUtil 
* @Description:文件工具类
* @author liujie
* @date 2017年7月19日 下午2:59:33 
*  
*/
public final class FileUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
	/** 
	* @Title: getRealFileName 
	* @Description: 获取文件名字(自动去掉文件路径) 
	* @param @param fileName
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	public static String getRealFileName(String fileName){
		return FilenameUtils.getName(fileName);
	}
	
	/** 
	* @Title: createFile 
	* @Description: 创建文件
	* @param @param filePath
	* @param @return    
	* @return File   
	* @throws 
	* @author
	*/
	public static File createFile(String filePath){
		File file;
		try{
			file = new File(filePath);
			File parentDir = file.getParentFile();
			if(!parentDir.exists()){
				FileUtils.forceMkdir(parentDir);
			}
		}catch(Exception e){
			LOGGER.error("create file failure",e);
			throw new RuntimeException(e);
		}
		return file;
	}

}
