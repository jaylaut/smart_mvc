package org.smart4.framework;

import org.smart4.framework.help.BeanHelper;
import org.smart4.framework.help.ClassHelper;
import org.smart4.framework.help.ControllerHelper;
import org.smart4.framework.help.IocHelper;
import org.smart4.framework.util.ClassUtil;

/** 
* @ClassName: HelperLoader 
* @Description: 加载Helper类
* @author 刘杰
* @date 2017年7月12日 下午3:37:45 
*  
*/
public final class HelperLoader {

	public static void init(){
		Class<?>[] classList = {
				ClassHelper.class,
				BeanHelper.class,
				IocHelper.class,
				ControllerHelper.class
		};
		for(Class<?> cls: classList){
			ClassUtil.loadClass(cls.getName(),false);
		}
	}
}
