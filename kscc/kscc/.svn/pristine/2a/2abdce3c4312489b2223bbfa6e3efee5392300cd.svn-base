/**
 * 
 */
package com.fable.kscc.api.utils;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 反射工具类
 * 
 * @author wuanguo
 * 
 */
public final class ReflectUtil extends BeanUtils {

	/**
     * 
     */
	private ReflectUtil() {
	}

	/**
	 * 获取对象接口的范型类型
	 * 
	 * @param object
	 * @return
	 */
	public static Class<?> getClassByInterfaces(Object object, int index) {
		return (Class<?>) ((ParameterizedType) object.getClass()
				.getGenericInterfaces()[0]).getActualTypeArguments()[index];
	}

	/**
	 * 获取对象父类范型类型
	 * 
	 * @param object
	 * @return
	 */
	public static Class<?> getClassBySuper(Object object) {
		return (Class<?>) ((ParameterizedType) object.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * 创建对象
	 * 
	 * @param className
	 *            类名称
	 * @param argsClass
	 *            构造方法参数类型数组
	 * @param args
	 *            构造方法参数值数组
	 * @return T 对象
	 */
	public static <T> T newInstance(String className, Class<?>[] argsClass,
			Object[] args) {

		try {
			return newInstance(Class.forName(className), argsClass, args);
		} catch (Exception e) {
			LoggerFactory.getLogger(ReflectUtil.class).error(e.getMessage());


		}
		return null;
	}

	/**
	 * 创建对象
	 * 
	 * @param clazz
	 *            Class类
	 * @param argsClass
	 *            构造方法参数类型数组
	 * @param args
	 *            构造方法参数值数组
	 * @return T 对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<?> clazz, Class<?>[] argsClass,
			Object[] args) {

		try {
			if (argsClass == null || argsClass.length <= 0) {
				return (T) clazz.newInstance();
			}

			return (T) clazz.getConstructor(argsClass).newInstance(args);
		} catch (Exception e) {
			LoggerFactory.getLogger(ReflectUtil.class).error(e.getMessage());

		}
		return null;
	}

	/**
	 * 执行对象方法
	 * 
	 * @param t
	 *            对象
	 * @param methodName
	 *            方法名
	 * @param argsClass
	 *            方法参数类型数组
	 * @param args
	 *            方法参数值数组
	 * @return Object 方法返回值
	 */
	public static <T> Object execMethod(T t, String methodName,
			Class<?>[] argsClass, Object[] args) {

		try {
			Method method = t.getClass().getMethod(methodName, argsClass);
			return method.invoke(t, args);
		} catch (Exception e) {
			LoggerFactory.getLogger(ReflectUtil.class).error(e.getMessage());
		}

		return null;
	}

	/**
	 * 判断方法是否存在
	 * 
	 * @param cls
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 */
	public static boolean isExistMethod(Class<?> cls, String methodName,
			Class<?>... parameterTypes) {

		try {
			Method method = cls.getDeclaredMethod(methodName, parameterTypes);
			if (method != null) {
				return true;
			}
		} catch (NoSuchMethodException e) {
			return false;
		} catch (Exception e) {
			LoggerFactory.getLogger(ReflectUtil.class).error(e.getMessage());
		}

		return false;
	}
	
	/**I
	 * 过滤排除空值的属性
	 * @param source
	 * @return
	 */
	private static String[] getNullPropertyNames (Object source) {
		 final BeanWrapper src = new BeanWrapperImpl(source);
		 java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		 Set<String> emptyNames = new HashSet<String>();
		 for(java.beans.PropertyDescriptor pd : pds) {
			 Object srcValue = src.getPropertyValue(pd.getName());
			 if (srcValue == null) 
				 emptyNames.add(pd.getName());
		 }
		 String[] result = new String[emptyNames.size()];
		 return emptyNames.toArray(result);
	}
	
	public static void copyProperties(Object source, Object target) throws BeansException {
		BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
	}

	
}
