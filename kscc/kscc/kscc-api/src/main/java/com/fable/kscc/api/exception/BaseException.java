package com.fable.kscc.api.exception;

import java.text.MessageFormat;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @author fangang 异常处理基类
 */
public class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String BUSSINESS_EXCEPTION_FILE = "/msg/rescodes.properties";

	protected static Properties exceptionLocaleProperties = new Properties();

	private String code;

	private Object[] args;

	private String message;

	private static Logger logger = LoggerFactory.getLogger(BaseException.class);

	static {
		// 装载业务异常配置文件
		try {
			Resource resource = new ClassPathResource(BUSSINESS_EXCEPTION_FILE);
			exceptionLocaleProperties = PropertiesLoaderUtils.loadProperties(resource);
		} catch (Exception e) {
			logger.error("装载业务异常配置文件失败，请检查业务异常配置文件exception.properties是否存在。");
		}
	}

	/**
	 * 构造方法
	 * 
	 * @param code
	 *            异常码
	 * @param args
	 *            参数
	 */
	public BaseException(String code, Object[] args) {
		this.code = code;
		this.args = args;
	}

	/**
	 * 构造方法
	 * 
	 * @param code
	 *            异常码
	 * @param cause
	 *            异常对象
	 * @param args
	 *            参数
	 */
	public BaseException(String code, Throwable cause, Object[] args) {
		super(cause);

		this.code = code;
		this.args = args;
	}

	public BaseException(String code) {
		this.code = code;
	}

	public BaseException(String code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		if (getExceptionMessage() != null) {
			// return getCode() + ":" + getExceptionMessage();
			return getExceptionMessage();
		}

		// return getCode() + ":" + getLocalizedMessage();
		return getLocalizedMessage();
	}

	/**
	 * @return
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * @return
	 */
	public Object[] getArgs() {
		return this.args;
	}

	/**
	 * @return
	 */
	public String getExceptionMessage() {
		return this.message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getLocalizedMessage()
	 */
	public String getLocalizedMessage() {
		if (getCode() == null) {
			logger.error("异常码为空，请检查抛出该异常的构造函数是否正确。");
		}

		if (("000000").equals(this.code)) {
			return MessageFormat.format(this.getCause().getMessage(), getArgs());
		}

		String message = exceptionLocaleProperties.getProperty(getCode());
		if (message == null) {

			//logger.error("异常码[" + getCode() + "]没有定义，请检查抛出该异常的构造函数是否正确。\r\n" + this.getCause().getMessage());
			return code;
		}

		return MessageFormat.format(message, getArgs());
	}
}
