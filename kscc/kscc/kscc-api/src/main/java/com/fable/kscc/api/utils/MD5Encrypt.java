package com.fable.kscc.api.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Administrator
 * MD5加密工具
 */
public class MD5Encrypt {
	
	private static final ThreadLocal<MD5Encrypt> local = new ThreadLocal<MD5Encrypt>();
	
	/**
	 * @return
	 */
	public static MD5Encrypt getEncrypt() {
		MD5Encrypt encrypt = local.get();
		if(encrypt == null) {
			encrypt = new MD5Encrypt();
			local.set(encrypt);
		}
		
		return encrypt;
	}

	/**
	 * @param s
	 * @return
	 */
	public static String encode(String s) {
		if(s == null) {
			return null;
		}
		
		return DigestUtils.md5Hex(s);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String passwd = "123456";
		System.out.println(passwd + " 加密后为： " + encode(passwd));

	}
}
