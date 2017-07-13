package org.smart4.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


/** 
* @ClassName: Request 
* @Description: 封装请求信息
* @author 刘杰
* @date 2017年7月12日 下午2:26:33 
*  
*/
public class Request {
	
	/** 
	* @Fields requestMethod : 请求方法
	*/ 
	private String requestMethod;
	
	/** 
	* @Fields requestPath : 请求路径 
	*/ 
	private String requestPath;

	public Request(String requestMethod, String requestPath) {
		super();
		this.requestMethod = requestMethod;
		this.requestPath = requestPath;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public String getRequestPath() {
		return requestPath;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(obj, this);
	}
	
	
	
	

}
