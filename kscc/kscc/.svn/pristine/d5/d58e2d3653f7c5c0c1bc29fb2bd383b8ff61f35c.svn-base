package com.fable.kscc.api.utils;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * 一些json与对象转换的工具集合类
 * 
 */
public final class JsonUtil {

	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private static final ObjectMapper objectMapper = new ObjectMapper();

	private JsonUtil() {
	}

	/**
	 * 将对象转化为json字符串
	 */
	public static String toJsonString(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * json字符串转化为 JavaBean
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toJavaBean(String content, Class<T> valueType) {
		if (valueType == String.class) {
			return (T) content;
		}
		try {
			return objectMapper.readValue(content, valueType);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * json字符串转化为list
	 */
	public static <T> List<T> toJavaBeanList(String content, TypeReference<List<T>> typeReference) {
		try {
			return objectMapper.readValue(content, typeReference);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 
	 * 此方法描述的是：将Object转化为Json格式字符串
	 * 
	 * @param obj
	 *            欲转换的对象
	 * @return String
	 */
	public static String toString(Object obj) {
		StringWriter writer = new StringWriter();
		JsonGenerator gen;
		String json = null;
		try {
			gen = new JsonFactory().createJsonGenerator(writer);
			objectMapper.writeValue(gen, obj);
			gen.close();
			json = writer.toString();
			writer.close();
		} catch (IOException e) {
			logger.error("Json转换错误");
		}
		return json;
	}

}