package org.smart4.framework.bean;


/** 
* @ClassName: FormField 
* @Description: 封装表单参数
* @author liujie
* @date 2017年7月18日 下午5:31:13 
*  
*/
public class FormParam {
	
	private String fieldName;
	
	private Object fieldValue;

	public FormParam(String fieldName, Object fieldValue) {
		super();
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	

}
