package com.fable.kscc.api.exception;

/**
 * 业务类自定义异常
 * @author duy 8.12
 */
@SuppressWarnings("serial")
public class BussinessException extends Exception {
	
	public BussinessException() {
		super();
	}
	/**
	 * 一般调用该方法就可以了，走该异常类，直接调用exception的实现..
	 * @param msg
	 */
	public BussinessException(String msg) {
		super(msg);
	}
	
	public BussinessException(String msg,Throwable cause) {
		super(msg, cause);
	}
}
