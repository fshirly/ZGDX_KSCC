package com.fable.kscc.api.utils;


import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.fabric.xmlrpc.base.Params;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fable.kscc.api.config.init.SysConfig;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author duyang 2017-08-05
 * 科达视讯平台登陆工具类
 */
public class KeDaLoginUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(KeDaLoginUtil.class);
	
	private static final String  url;
	private static final String  username;
	private static final String  password;
	private static final String  key;
	private static final String  value;
	
	static {
//		url = SysConfig.getValueByKey("keda.host");
//		username = SysConfig.getValueByKey("keda.username");
//		password = SysConfig.getValueByKey("keda.password");
//		key = SysConfig.getValueByKey("keda.oauth.key");
//		value = SysConfig.getValueByKey("keda.oauth.value");
		url = "http://58.211.191.7:90/";
		username = "lixiang";
		password = "888888";
		key = "kdvpb";
		value = "admin888";
//		keda.host=http://58.211.191.7:90/
//		keda.username=lixiang
//		keda.password=888888
//		keda.oauth.key=kdvpb
//		keda.oauth.value=admin888

	}

	/**
	 * 获取科达登陆信息
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getLoginInfo() {
		Map<String,Object> res = new HashMap<String,Object>();
		boolean success = false;
		try {
			String token = getKeDaToken();
			System.out.println(token);
			String ip = url+Constants.KEDA_INTERFACE_ADDRESS.LOGIN;
			String param = "account_token="+token+"&username="+username+"&password="+password;
			res = RestHepler.sendHttpRequest(ip, Map.class, param, new Object());
			if(res != null && Constants.KEDA_INTERFACE_MSG.SUCCESS.equals(res.get("success").toString())) {
				success = true;
			}
			LOGGER.info("科达登陆信息调用成功，msg={}", res);
		} catch (Exception e) {
			LOGGER.error("The request failed，msg={}", e);
			e.printStackTrace();
		}
		res.put("success", success);
		return res;
	}

	/**
	 * 获取科达token
	 */
	@SuppressWarnings("unchecked")
	public static String getKeDaToken() {
		Map<String,Object> resMap = new HashMap<String,Object>();
		String res = "";
		try {
			String param = "oauth_consumer_key="+key+"&oauth_consumer_secret="+value;
			String ip = url+Constants.KEDA_INTERFACE_ADDRESS.TOKEN;
			resMap = RestHepler.sendHttpRequest(ip, Map.class, param, new Object());
			if(resMap != null && Constants.KEDA_INTERFACE_MSG.SUCCESS.equals(resMap.get("success").toString())) {
				res = (String) resMap.get("account_token");
				LOGGER.info("科达登陆信息调用成功，msg={}", res);
			} else {
				LOGGER.info("科达登陆信息调用失败!");
			}
		} catch (Exception e) {
			LOGGER.error("The request failed，msg={}", e);
			e.printStackTrace();
		}
		return res;
	}


	public static void main(String[] args) {
		System.out.println(getLoginInfo());
	}
}
