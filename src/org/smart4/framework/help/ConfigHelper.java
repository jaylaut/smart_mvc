package org.smart4.framework.help;

import java.util.Properties;

import org.smart4.framework.ConfigConstant;
import org.smart4.framework.util.PropsUtil;

/** 
* @ClassName: ConfigHelper 
* @Description: 属性文件工具类
* @author 刘杰
* @date 2017年7月11日 上午8:41:34 
*  
*/
public final class ConfigHelper {

	
	/** 
	* @Fields CONFIG_PROPS : 获取属性文件
	*/ 
	private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);
	
	/** 
	* @Title: getJdbcDriver 
	* @Description: 获取jdbc驱动
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	public static String getJdbcDriver(){
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
	}
	
	/** 
	* @Title: getJdbcUrl 
	* @Description: 获取url 
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	public static String getJdbcUrl(){
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
	}
	
	/** 
	* @Title: getJdbcUsername 
	* @Description: 获取用户名
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	public static String getJdbcUsername(){
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
	}
	
	/** 
	* @Title: getJdbcPassword 
	* @Description: 获取jdbc密码
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	public static String getJdbcPassword(){
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
	}
	
	/** 
	* @Title: getAppBasePackage 
	* @Description: 获取应用基础包名
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	public static String getAppBasePackage(){
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
	}
	
	/** 
	* @Title: getAppJspPath 
	* @Description: 获取jsp路径
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	public static String getAppJspPath(){
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH,"/WEB-INF/view/");
	}
	
	/** 
	* @Title: getAppAssetPath 
	* @Description: 获取静态资源路径
	* @param @return    
	* @return String   
	* @throws 
	* @author
	*/
	public static String getAppAssetPath(){
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH,"/asset/");
	}
	
	/** 
	* @Title: getAppUploadLimit 
	* @Description:获取文档上传最大的限制 
	* @param @return    
	* @return int   
	* @throws 
	* @author
	*/
	public static int getAppUploadLimit(){
		return PropsUtil.getInt(CONFIG_PROPS, ConfigConstant.APP_UPLOAD_LIMIT,10);
	}
}
