package com.fable.kscc.api.beans;

/**
 * hyt增加  获取token时5.0平台回复参数
 * @author heyutong
 * 2016年11月10日
 */
public class OauthTokenResp {
	private int success;
	private String account_token;	//String默认是null
	private int error_code;			//int型默认是0
	
	public OauthTokenResp(){}		//单例模式
	
	public int getError_code() {
		return error_code;
	}
	
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	@Override
	public String toString() {
		return "OauthTokenCResp [success=" + success + ", account_token=" + account_token + ", error_code=" + error_code
				+ "]";
	}

	public synchronized int getSuccess() {
		return success;
	}
	public synchronized void setSuccess(int success) {
		this.success = success;
	}
	
	public synchronized String getAccount_token() {
		return account_token;
	}
	public synchronized void setAccount_token(String account_token) {
		this.account_token = account_token;
	}
}
