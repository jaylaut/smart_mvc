package org.smart4.framework.help;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4.framework.annotation.Inject;
import org.smart4.framework.util.CollectionUtil;
import org.smart4.framework.util.ReflectionUtil;

/** 
* @ClassName: IocHelper 
* @Description: IOC控制类 
* @author 刘杰
* @date 2017年7月12日 下午2:31:11 
*  
*/
public final class IocHelper {

	static{
		Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
		if(CollectionUtil.isNotEmpty(beanMap)){
			//遍历bean map
			for(Map.Entry<Class<?>, Object> beanFactory : beanMap.entrySet()){
				//从beanMap 获取bean类和bean实例
				Class<?> beanClass = beanFactory.getKey();
				Object beanInstance = beanFactory.getValue();
				//从获取的beanClass获取成员变量
				Field[] beanFields = beanClass.getDeclaredFields();
				if(ArrayUtils.isNotEmpty(beanFields)){
					//遍历beanFields
					for(Field field : beanFields){
						//判断bean field 是否有Inject注解
						if(field.isAnnotationPresent(Inject.class)){
							Class<?> beanFieldClass = field.getType();
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							if(beanFieldInstance != null){
								//通过反射初始化field的值
								ReflectionUtil.setField(beanInstance, field, beanFieldInstance);
							}
						}
					}
				}
			}
		}
	}
}
