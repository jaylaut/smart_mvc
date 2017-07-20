package org.smart4.framework.bean;

import java.io.InputStream;

/** 
* @ClassName: FileParam 
* @Description: 封装上传文件参数
* @author liujie
* @date 2017年7月18日 下午5:27:21 
*  
*/
public class FileParam {
	
	/** 
	* @Fields fieldName : 文件表单字段名 
	*/ 
	private String fieldName;
	
	private String fileName;
	
	private long fileSize;
	
	private String contentType;
	
	private InputStream inputStream;

	public FileParam(String fieldName, String fileName, long fileSize, String contentType, InputStream inputStream) {
		super();
		this.fieldName = fieldName;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.contentType = contentType;
		this.inputStream = inputStream;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	

}
