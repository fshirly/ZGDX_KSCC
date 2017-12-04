package com.fable.kscc.api.config.init;
import java.util.ResourceBundle;

/**
 * 
 * @author duyang 
 * add by 2017-08-05 加载系统配置文件 
 */
public class SysConfig {

	private static ResourceBundle systemParamBundle;
	
	/**
	 * 初始化
	 */
	static {
		if(null == systemParamBundle){
			systemParamBundle = ResourceBundle.getBundle("sysconfig");
		}
	}

	/**
	 * 根据配置文件里面的key获取值
	 * @param key
	 * @return
	 */
	public static String getValueByKey(String key){
		return systemParamBundle.getString(key);
	}
	
}
