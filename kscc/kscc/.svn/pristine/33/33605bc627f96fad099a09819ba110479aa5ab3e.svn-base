package com.fable.kscc.api.beans;

import com.fable.kscc.api.config.init.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiServerConfig {

	private static final String ip ; //apiserver的地址
	private static final String port;//端口
	private static final String moid;//5.0的moid

	static{
		ip = SysConfig.getValueByKey("keda.ip");
		port = SysConfig.getValueByKey("keda.port");
		moid = SysConfig.getValueByKey("keda.moid");
	}
	
	public ApiServerConfig() {}
	
	public static String getSocketAddr() {
		return String.format("%s:%s", ip, port) ;
	}

}
