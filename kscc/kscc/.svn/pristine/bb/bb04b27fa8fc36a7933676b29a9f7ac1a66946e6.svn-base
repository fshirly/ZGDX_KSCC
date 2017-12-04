package com.fable.kscc.api.utils;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * JSON工具类，提供JSON与Bean之间的相互转换。
 * @author admin
 */
public class JsonUtils {

	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss" ;

	public static String toJson(Object obj) {
		try{
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(DEFAULT_DATE_PATTERN));
			JSONObject jobj = JSONObject.fromObject(obj, config) ;
			return jobj.toString() ;
		} catch(Exception e){
			e.printStackTrace();
			return null ;
		}
	}
	
	public static String toJson(List<String> list) {
		try{
			JSONArray jobj = JSONArray.fromObject(list) ;
			return jobj.toString() ;
		} catch(Exception e){
			e.printStackTrace(); 
			return null ;
		}
	}
	
	/**
	 * JSON转化成Bean
	 * @param jsonStr
	 * @param beanClass：要转换的Bean的Class
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toBean(String jsonStr, Class<T> beanClass) {
		JSONObject jobj = JSONObject.fromObject(jsonStr) ;
		return (T) JSONObject.toBean(jobj, beanClass) ;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T toBeanFromJsonArray(String jsonArray, Class<T> beanClass) {
		JSONArray jarr = JSONArray.fromObject(jsonArray) ;
		return (T) JSONArray.toCollection(jarr, beanClass) ;
	}
	
	public static boolean isJson(String jsonStr) {
		try {
			JSONObject.fromObject(jsonStr) ;
			return true ;
		} catch(Exception e) {
			return false ;
		}
	}
	
	public static boolean isBadGateWay(String jsonStr) {
		if(jsonStr.contains("502")) {
			return true ;
		}
		return false ;
	}
	
	/**
	 * JSON转化成Bean（有嵌套对象属性的类）
	 * @param jsonStr
	 * @param beanClass：要转换的Bean的Class
	 * @param propClassMap：Bean所包含的对象属性与对象属性Class值得Map
	 * @return T
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T toBean(String jsonStr, Class<T> beanClass, Map<String, Class> propClassMap) {
		JSONObject jobj = JSONObject.fromObject(jsonStr) ;
		return (T) JSONObject.toBean(jobj, beanClass, propClassMap) ;
	}
	
	public static String exceptionJson(String errorMsg) {
		JSONObject jobj = new JSONObject() ;
		jobj.put("exception", errorMsg) ;
		return jobj.toString() ;
	}
	
	public static String errorJson(String errorMsg) {
		JSONObject jobj = new JSONObject() ;
		jobj.put("error_code", errorMsg) ;
		return jobj.toString() ;
	}

	/**
	 * 从JSON字符串中提取某个字段值
	 * @param jsonStr
	 * @param filedName
	 * @return
	 */
	public static String extractFiledValue(String jsonStr, String filedName) {
		try {
			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { DEFAULT_DATE_PATTERN }));
			JSONObject jobj = JSONObject.fromObject(jsonStr) ;
			return jobj.optString(filedName) ;
		} catch(Exception e) {
			e.printStackTrace() ;
		}
		return null ;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> toBeanCollection(String jsonArray, Class<T> beanClass) {
		JSONArray jarr = JSONArray.fromObject(jsonArray);
		return (List<T>) JSONArray.toCollection(jarr, beanClass);
	}
		
	public static <T> String toJsonFromCollection(Collection<T> colecction) {
		try {
			JSONArray jobj = JSONArray.fromObject(colecction);
			return jobj.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}


 