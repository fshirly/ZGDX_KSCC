package com.fable.kscc.api.beans;
/**
 * 保存登陆成功的回复参数，int success/String username/int error_code/String cookie
 * @author heyutong
 * 2016年11月10日
 */
public class LoginMcuResp {
	private int success;
	private String username;
	private int error_code;
	private String cookie ;
	private LoginMcuResp() {
	}
	
	private static LoginMcuResp loginMcuResp = new LoginMcuResp();
	
	public static LoginMcuResp setLoginMcuResp(LoginMcuResp login) {
		if(login != null) {
		loginMcuResp.setSuccess(login.getSuccess());
		loginMcuResp.setUsername(login.getUsername());
		loginMcuResp.setError_code(login.getError_code());
		}
		return loginMcuResp;
	}
	
	public static LoginMcuResp getinstance() {
		return loginMcuResp;
	}
	public void setSuccess(int success) {
		this.success = success;
	}

	public int getSuccess() {
		return success;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}	
	
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	
	public int getError_code() {
		return error_code;
	}
	
	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	@Override
	public String toString() {
		return "LoginMcuResp [success=" + success + ", username=" + username + ", error_code=" + error_code + "]";
	}

}
