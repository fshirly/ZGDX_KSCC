package com.fable.kscc.api.utils;

import java.util.UUID;

/*import org.junit.Test;*/

/**
 * 字符串工具类
 * @author duy 2016 08 14
 *
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		boolean res = false;
		if (str != null && str != "") {
			res = true;
		}
		return res;
	}
	
	/**
	 * 获取32位主键字符串
	 * @return
	 */
	public static String get32UUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	} 
	
	
	/*@Test
	public void test() {
		String uid = get32UUID();
		System.out.println(uid +" 长度="+uid.length());
	}*/
	
}
