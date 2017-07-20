package org.smart4.framework.help;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4.framework.bean.FileParam;
import org.smart4.framework.bean.FormParam;
import org.smart4.framework.bean.Param;
import org.smart4.framework.util.CollectionUtil;
import org.smart4.framework.util.FileUtil;
import org.smart4.framework.util.StreamUtil;
import org.smart4.framework.util.StringUtil;

/** 
* @ClassName: UploadHelper 
* @Description: 文件上传类助手
* @author liujie
* @date 2017年7月18日 下午6:14:46 
*  
*/
public final class UploadHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadHelper.class);
	
	private static ServletFileUpload servletFileUpload;
	
	/** 
	* @Title: init 
	* @Description: 初始化
	* @param @param servletContext    
	* @return void   
	* @throws 
	* @author
	*/
	public static void init(ServletContext servletContext){
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		servletFileUpload = new ServletFileUpload(new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD,repository));
		int uploadLimit = ConfigHelper.getAppUploadLimit();
		if(uploadLimit != 0){
			servletFileUpload.setFileSizeMax(uploadLimit*1024*1024);
		}
	}
	
	/** 
	* @Title: isMultipart 
	* @Description: 判断请求是否为multipart类型 
	* @param @param request
	* @param @return    
	* @return boolean   
	* @throws 
	* @author
	*/
	public static boolean isMultipart(HttpServletRequest request){
		return ServletFileUpload.isMultipartContent(request);
	}
	
	/** 
	* @Title: createParam 
	* @Description: 创建请求对象 
	* @param @param request
	* @param @return
	* @param @throws IOException    
	* @return Param   
	* @throws 
	* @author
	*/
	public static Param createParam(HttpServletRequest request) throws IOException{
		List<FormParam> formParamList = new ArrayList<FormParam>();
		List<FileParam> fileParamList = new ArrayList<FileParam>();
		try{
			Map<String,List<FileItem>> fileItemListMap = servletFileUpload.parseParameterMap(request);
			if(CollectionUtil.isNotEmpty(fileItemListMap)){
				for(Map.Entry<String,List<FileItem>> fileItemListEntry : fileItemListMap.entrySet()){
					String fieldName = fileItemListEntry.getKey();
					List<FileItem> fileItemList= fileItemListEntry.getValue();
					if(CollectionUtil.isNotEmpty(fileItemList)){
						for(FileItem fileItem : fileItemList){
							if(fileItem.isFormField()){
								String fieldValue = fileItem.getString("UTF-8");
								formParamList.add(new FormParam(fieldName,fieldValue));
							}else{
								String fileName = FileUtil.getRealFileName(new String(fileItem.getName().getBytes("UTF-8")));
								if(StringUtil.isNotEmpty(fileName)){
									long fileSize = fileItem.getSize();
									String contentType = fileItem.getContentType();
									InputStream inputStream = fileItem.getInputStream();
									fileParamList.add(new FileParam(fieldName,fileName,fileSize,contentType,inputStream));
								}
							}
						}
					}
				}
			}
		}catch(FileUploadException e){
			LOGGER.error("create file failure",e);
			throw new RuntimeException(e);
		}
		return new Param(formParamList,fileParamList);
	}
	
	/** 
	* @Title: uploadFile 
	* @Description:上传文档
	* @param @param basePath
	* @param @param fileParam    
	* @return void   
	* @throws 
	* @author
	*/
	public static void uploadFile(String basePath,FileParam fileParam){
		try{
			if(fileParam!= null){
				String filePath = basePath + fileParam.getFileName();
				FileUtil.createFile(filePath);
				InputStream inputStream = new BufferedInputStream(fileParam.getInputStream());
				OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
				StreamUtil.copyStream(inputStream,outputStream);
			}
		}catch(Exception e){
			LOGGER.error("upload file failure",e);
			throw new RuntimeException(e);
		}
	}
	
	/** 
	* @Title: uploadFile 
	* @Description: 批量上传文档
	* @param @param basePath
	* @param @param fileParamList    
	* @return void   
	* @throws 
	* @author
	*/
	public static void uploadFile(String basePath,List<FileParam> fileParamList){
		try{
			if(CollectionUtil.isNotEmpty(fileParamList)){
				for(FileParam fileParam : fileParamList){
					uploadFile(basePath,fileParam);
				}
			}
		}catch(Exception e){
			LOGGER.error("batch file upload failure",e);
			throw new RuntimeException(e);
		}
	}

}
