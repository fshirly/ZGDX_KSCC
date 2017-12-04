package com.fable.kscc.api.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Tool {

	public static UUID newGuid() {
		return UUID.randomUUID();
	}

	public static Date Now() {
		Date dt = new Date(System.currentTimeMillis());
		return dt;
	}

	public static String nowToString() {
		Date dt = new Date();
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd");
		return from.format(dt);
	}

	public static String IsNull(String strValue, String strReturn) {
		if (strValue == null)
			return Nothing.nullString;
		else
			return strReturn;
	}

	public static String[] Split(String strValue) {
		if (Nothing.IsNull(strValue))
			return new String[0];
		return strValue.split(",");
	}

	public static String[] Split(String strValue, String strSplit) {
		if (Nothing.IsNull(strValue))
			return new String[0];
		return strValue.split(strSplit);
	}

	public static ArrayList<String> SplitArrayList(String strValue) {
		ArrayList<String> reArr = new ArrayList<String>();
		if (Nothing.IsNull(strValue))
			return reArr;
		String[] strArr = strValue.split(",");

		for (String str : strArr)
			reArr.add(str);
		return reArr;
	}

	public static ArrayList<String> SplitArrayList(String strValue,
			String strSplit) {
		ArrayList<String> reArr = new ArrayList<String>();
		if (Nothing.IsNull(strValue))
			return reArr;
		String[] strArr = strValue.split(strSplit);

		for (String str : strArr)
			reArr.add(str);
		return reArr;
	}

	public static String Merge(String[] strValue) {
		StringBuilder sb = new StringBuilder();
		for (String str : strValue) {
			if (sb.length() > 0)
				sb.append(",");
			sb.append(str);
		}
		return sb.toString();
	}

	public static String Merge(String[] strValue, String strSplit) {
		StringBuilder sb = new StringBuilder();
		for (String str : strValue) {
			if (sb.length() > 0)
				sb.append(strSplit);
			sb.append(str);
		}
		return sb.toString();
	}

//	public static String InputFilter(String strInput, FilterType ft) {
//		if (Nothing.IsNull(strInput))
//			return Nothing.nullString;
//		switch (ft) {
//		case NoSQL:
//			return RemoveSQL(strInput);
//		case NoScript:
//			return RemoveScript(strInput);
//		case NoMarkup:
//			return RemoveMarkup(strInput);
//		case MultiLine:
//			return RemoveMultiLine(strInput);
//		}
//		return strInput;
//	}

	public static Object GetBean(String strName) {
		ApplicationContext ctx;

		try {
			ctx = new FileSystemXmlApplicationContext(
					"classpath:applicationContext.xml");

			return ctx.getBean(strName);
		} finally {
		}

	}

	public static <T> List<T> boToListBo(T t) {
		List<T> lo = new ArrayList<T>();
		lo.add(t);
		return lo;
	}

	private static String RemoveSQL(String strValue) {
		String reStr;
		reStr = strValue;

		String[] strSQL = Split("create,drop,select,insert,update,union,sp_,grant,--");

		for (String str : strSQL)
			reStr = reStr.replaceAll(str, " ");
		reStr = reStr.replaceAll("'", "''");
		return reStr;
	}

	private static String RemoveScript(String strValue) {
		String reStr;
		reStr = strValue;

		// Pattern p= Pattern.compile("(?i)");
		// Matcher m= p.matcher(reStr);
		// m.replaceAll(" ");

		reStr = reStr.replaceAll("<script[^>]*>[\\d\\D]*?</script>", "");
		return reStr;
	}

//	private static String RemoveMarkup(String strValue) {
//		String reStr;
//		reStr = strValue;
//		return StringEscapeUtils.unescapeHtml(reStr);
//	}

	private static String RemoveMultiLine(String strValue) {
		String reStr;
		reStr = strValue.replaceAll("\r\n", "<br>");

		reStr = strValue.replaceAll("\r", "<br>");
		reStr = strValue.replaceAll("\n", "<br>");
		return reStr;
	}

	/**
	 * 去除元素前后的双引号
	 * 
	 * @author nimj
	 */
	public static String[] removeQuotes(String[] strAry) {
		int strAryLen = strAry == null ? 0 : strAry.length;
		String[] newStrAry = new String[strAryLen];
		String str = null;
		for (int i = 0; i < strAryLen; i++) {
			str = strAry[i];
			newStrAry[i] = str.substring(1, str.length() - 1);
		}
		return newStrAry;
	}

//	public static Message getSuccMsgBean(String msg) {
//		Message msgBean = new Message();
//		msgBean.setMessage(msg);
//		return msgBean;
//	}
//
//	public static JSONObject getJsonObj(String key, String value) {
//		JSONObject jsonObj = new JSONObject();
//		jsonObj.put(key, value);
//		return jsonObj;
//	}
//
//	public static JSONArray getJsonAry(JSONObject... jsonObjs) {
//		JSONArray jsonAry = new JSONArray();
//		for (JSONObject jsonObj : jsonObjs) {
//			jsonAry.add(jsonObj);
//		}
//		return jsonAry;
//	}

	public static void main(String[] args) {
		System.out.println(newGuid());
	}
}
