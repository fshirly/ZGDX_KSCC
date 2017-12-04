package com.fable.kscc.api.utils;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.UUID;

public class Nothing {

	public static int nullInt = -1;
	public static boolean nullBoolean = false;
	public static Date nullDate = null;
	public static String nullString = "";
	public static UUID nullGuid = UUID
			.fromString("00000000-0000-0000-0000-000000000000");
	public static String nullGuidStr = "00000000000000000000000000000000";

	public static Object GetNull(Object obj) {
		if (obj == null) {
			return nullString;
		} else {
			if (obj instanceof Integer || obj instanceof Float
					|| obj instanceof Double || obj instanceof Long) {
				return nullInt;
			} else if (obj instanceof Boolean) {
				return nullBoolean;
			} else if (obj instanceof String) {
				return nullString;
			} else if (obj instanceof UUID) {
				return nullGuid;
			} else if (obj instanceof Date) {
				return nullDate;
			} else {
				return nullString;
			}
		}
	}

	public static boolean IsNull(Object obj) {
		if (obj == null)
			return true;
		else {
			if (obj instanceof Boolean)
				return false;

			if (obj.getClass().isArray())
				return Array.getLength(obj) > 0;
				
				if (obj.getClass().isArray() )
					return Array.getLength(obj) > 0;				

			return obj.equals(GetNull(obj));
		}
	}
	
	/**
	 * jsp导出word时，解决中文文件名乱码
	 * @param gb2312Str
	 * @return
	 */
	public static String UniC(String gb2312Str) {
		String unicoStr = "";
		if (gb2312Str == null) {
			gb2312Str = "";
		}
		try {
			byte[] yte = gb2312Str.getBytes("GB2312");
			unicoStr = new String(yte, "ISO8859_1");
		} catch (Exception ex) {
		}
		return unicoStr;
	}

}
