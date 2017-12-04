package com.fable.kscc.api.beans;

import com.fable.kscc.api.config.init.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jack
 * 2016年11月10日
 */
public class LoginMcuConfig {
	private static final String  username;
	private static final String  password;
	static {
		username = SysConfig.getValueByKey("keda.username");
		password = SysConfig.getValueByKey("keda.password");
	}

	private Logger logger = LoggerFactory.getLogger(LoginMcuConfig.class) ;
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}

	public void print(){
		logger.info(String.format("[LoginMcuConfig]:username=%s,password=%s",username, password));
	}
}
