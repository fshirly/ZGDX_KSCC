package com.fable.kscc.api.beans;

import com.fable.kscc.api.config.init.SysConfig;

/**
 * @author jack 
 * @date 2016年11月10日
 */
public class OauthConsumerInfo {

	private static final String  oauthConsumerKey;
	private static final String  oauthConsumerSecret;

	static{
		oauthConsumerKey = SysConfig.getValueByKey("keda.oauth.key");
		oauthConsumerSecret = SysConfig.getValueByKey("keda.oauth.value");
	}
	@Override
	public String toString() {
		return "OauthConsumerInfo [oauthConsumerKey=" + oauthConsumerKey + ", oauthConsumerSecret=" + oauthConsumerSecret
				+ "]";
	}

	public String getOauthConsumerKey() {
		return oauthConsumerKey;
	}


	public String getOauthConsumerSecret() {
		return oauthConsumerSecret;
	}

}
