package org.smart4.framework.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.smart4.framework.util.CaseUtil;
import org.smart4.framework.util.CollectionUtil;
import org.smart4.framework.util.StringUtil;

/** 
* @ClassName: Param 
* @Description: 请求参数对象 
* @author 刘杰
* @date 2017年7月12日 下午3:44:03 
*  
*/
public class Param {
	
	private List<FormParam> formParamList;
	
	private List<FileParam> fileParamList;

	private Map<String,Object> paramMap;

	public Param(List<FormParam> formParamList) {
		super();
		this.formParamList = formParamList;
	}
	
	public Param(List<FormParam> formParamList, List<FileParam> fileParamList) {
		super();
		this.formParamList = formParamList;
		this.fileParamList = fileParamList;
	}



	public Param(Map<String, Object> paramMap) {
		super();
		this.paramMap = paramMap;
	}
	
	/** 
	* @Title: getFieldMap 
	* @Description: 获取请求参数映射 
	* @param @return    
	* @return Map<String,Object>   
	* @throws 
	* @author
	*/
	public Map<String,Object> getFieldMap(){
		Map<String,Object> fieldMap = new HashMap<String,Object>();
		for(FormParam formParam : formParamList){
			String fieldName = formParam.getFieldName();
			Object fieldValue = formParam.getFieldValue();
			if(fieldMap.containsKey(fieldName)){
				fieldValue = fieldMap.get(fieldName)+StringUtil.SEPARATOR+fieldValue;
			}
			fieldMap.put(fieldName, fieldValue);
		}
		return fieldMap;
	}
	
	/** 
	* @Title: getFileMap 
	* @Description: 获取上传文件映射
	* @param @return    
	* @return Map<String,List<FileParam>>   
	* @throws 
	* @author
	*/
	public Map<String,List<FileParam>> getFileMap(){
		Map<String,List<FileParam>> fileMap = new HashMap<String,List<FileParam>>();
		if(CollectionUtil.isNotEmpty(fileParamList)){
			for(FileParam fileParam : fileParamList){
				String fieldName = fileParam.getFileName();
				List<FileParam> fileParamList;
				if(fileMap.containsKey(fieldName)){
					fileParamList = fileMap.get(fieldName);
				}else{
					fileParamList = new ArrayList<FileParam>();
				}
				fileParamList.add(fileParam);
				fileMap.put(fieldName, fileParamList);
			}
		}
		return fileMap;
	}
	
	/** 
	* @Title: getFileList 
	* @Description: 获取所有上传文件	 
	* @param @param fileName
	* @param @return    
	* @return List<FileParam>   
	* @throws 
	* @author
	*/
	public List<FileParam> getFileList(String fileName){
		return getFileMap().get(fileName);
	}
	
	/** 
	* @Title: getFile 
	* @Description: 获取唯一上传文件 
	* @param @param fileName
	* @param @return    
	* @return FileParam   
	* @throws 
	* @author
	*/
	public FileParam getFile(String fileName){
		List<FileParam> fileParamList = getFileList(fileName);
		if(CollectionUtil.isNotEmpty(fileParamList)&&fileParamList.size() == 1){
			return fileParamList.get(0);
		}
		return null;
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
		return CollectionUtil.isEmpty(fileParamList)&&CollectionUtil.isEmpty(formParamList);
	}
	
	/** 
	* @Title: getString 
	* @Description: 根据参数名获取String类型参数 
	* @param @param name
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	public String getString(String name){
		return CaseUtil.caseString(getFileMap().get(name));
	}
	
	/** 
	* @Title: getDouble 
	* @Description: 根据参数名获取double类型参数 
	* @param @param name
	* @param @return    
	* @return double   
	* @throws 
	* @author
	*/
	public double getDouble(String name){
		return CaseUtil.caseDouble(getFileMap().get(name));
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
		return CaseUtil.caseLong(getFileMap().get(name));
	}
	
	/** 
	* @Title: getInt 
	* @Description: 根据参数名获取int参数值 
	* @param @param name
	* @param @return    
	* @return int   
	* @throws 
	* @author
	*/
	public int getInt(String name){
		return CaseUtil.caseInt(getFileMap().get(name));
	}
	
	/** 
	* @Title: getBoolean 
	* @Description: 根据参数名获取boolean参数值
	* @param @param name
	* @param @return    
	* @return boolean   
	* @throws 
	* @author
	*/
	public boolean getBoolean(String name){
		return CaseUtil.caseBoolean(getFileMap().get(name));
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
	/*public boolean isEmpty(){
		return CollectionUtil.isEmpty(paramMap);
	}*/
}
