package org.smart4.framework.help;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.smart4.framework.bean.FormParam;
import org.smart4.framework.bean.Param;
import org.smart4.framework.util.ArrayUtil;
import org.smart4.framework.util.CodecUtil;
import org.smart4.framework.util.StreamUtil;
import org.smart4.framework.util.StringUtil;

/** 
* @ClassName: RequestHelper 
* @Description:请求助手类
* @author liujie
* @date 2017年7月19日 下午3:30:59 
*  
*/
public final class RequestHelper {
	
	public static Param createParam(HttpServletRequest request) throws IOException{
		List<FormParam> formParamList = new ArrayList<FormParam>();
		formParamList.addAll(parseParameterNames(request));
		formParamList.addAll(parseInputStream(request));
		return new Param(formParamList);
	}
	
	private static List<FormParam> parseParameterNames(HttpServletRequest request){
		List<FormParam> formParamList = new ArrayList<FormParam>();
		Enumeration<String> paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()){
			String fieldName = paramNames.nextElement();
			String [] fieldValues = request.getParameterValues(fieldName);
			if(ArrayUtil.isNotEmpty(fieldValues)){
				Object fieldValue;
				if(fieldValues.length==1){
					fieldValue = fieldValues[0];
				}else{
					StringBuilder sb = new StringBuilder("");
					for(int i=0;i<fieldValues.length;i++){
						sb.append(fieldValues[i]);
						if(1 != fieldValues.length - 1){
							sb.append(StringUtil.SEPARATOR);
						}
					}
					fieldValue = sb.toString();
				}
				formParamList.add(new FormParam(fieldName,fieldValue));
			}
		}
		return formParamList;
	}
	
	
	private static List<FormParam> parseInputStream(HttpServletRequest request) throws IOException{
		List<FormParam> formParamList = new ArrayList<FormParam>();
		String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
		if(StringUtil.isNotEmpty(body)){
			String[] kvs = body.split("&");
			if(ArrayUtil.isNotEmpty(kvs)){
				for(String kv: kvs){
					String [] array = kv.split("=");
					if(ArrayUtil.isNotEmpty(array) && array.length == 2){
						String fieldName = array[0];
						String fieldValue = array[1];
						formParamList.add(new FormParam(fieldName,fieldValue));
					}
				}
			}
		}
		return formParamList;
	}

}
