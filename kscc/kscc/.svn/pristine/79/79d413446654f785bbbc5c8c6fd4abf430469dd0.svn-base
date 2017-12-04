package com.fable.kscc.api.utils;

import com.fable.kscc.api.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


/**
 * 常用日期相关操作
 * 
 * @author wuanguo
 */
public final class DateTimeUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	/**
     *
     */
	private DateTimeUtil() {
		// TODO Auto-generated constructor stub
	}

	public static  String getStringToTime(String dateTime, String pattern) {
		return String.valueOf(getStringToDate(dateTime, pattern).getTime());

	}

	public static  Date getStringToDate(String dateTime, String pattern) {

		try {
			DateFormat df = new SimpleDateFormat(pattern);
			return df.parse(dateTime);
		} catch (ParseException e) {
			LoggerFactory.getLogger(ReflectUtil.class).error(e.getMessage());
		}

		return null;
	}

	public static String dateToString(Date dt) {
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd");
		return from.format(dt);
	}

	public static String dateToString(Date dt, String strformat) {
		SimpleDateFormat from = new SimpleDateFormat(strformat);
		return from.format(dt);
	}

	public static String toString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static final Date toDate(String dateTime) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将Timezone时间转换为本地时间（如将2014-08-23T09:20:05.375000Z格式的日期转换为2014/08/23
	 * 09:20:05）
	 */
	public static String getFormattedStrDate(String srcDate, String srcFormat,
			String destFormat) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(srcFormat);
			format.setTimeZone(TimeZone.getTimeZone("UTC"));
			return new SimpleDateFormat(destFormat).format(format
					.parse(srcDate));
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage(), e, null);
		}
	}

	/**
	 * 将Timezone时间转换为本地时间（如将2014-08-23T09:20:05.375000Z格式的日期转换为2014/08/23
	 * 09:20:05）
	 */
	public static Date fromTimeZoneDateToFormatDate(String timeZoneDate,
			String destFormat) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss");
			format.setTimeZone(TimeZone.getTimeZone("UTC"));
			String date = new SimpleDateFormat(destFormat).format(format
					.parse(timeZoneDate));
			return getStringToDate(date, destFormat);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage(), e, null);
		}
	}

	/**
	 *
	 * @param gmtDate 字符串gmt时间（如：2017-10-13 12:24:08）
	 * @return UTC时间 如 Fri Oct 13 20:22:54 CST 2017
	 * 适用于调用编解码器文件查询（文件备份）接口
	 */
	public static String gmtToUtc(String gmtDate){
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			format.setTimeZone(TimeZone.getTimeZone("UTC"));
			String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(format
					.parse(gmtDate));
			System.out.println(date);
			return getStringToTime(date, "yyyy-MM-dd'T'HH:mm:ss");
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage(), e, null);
		}
	}
	/**
	 *
	 * @param gmtDate gmt时间
	 * @return UTC时间 如 2017-10-13T12:24:08
	 * description 适用于调用编解码器时间同步接口参数
	 */
	public static String gmtToUtc(Date gmtDate){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//			format.setTimeZone(TimeZone.getTimeZone("UTC"));,此处若加上就是-08:00转换，依据调用编解码器设定时间接口不需要，
			//参数中已经给了+08:00
			return format.format(gmtDate);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage(), e, null);
		}
	}

	public static  String stringDateToStringTime(String date){
		try {
			return String.valueOf(simpleDateFormat.parse(date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws ParseException {
//		System.out.println("gmt Time:::"+stringDateToStringTime("2017-10-13 12:54:26"));
//		System.out.println("utc Time:::"+DateTimeUtil.gmtToUtc(
//				"1970-01-01 00:00:00"));
//		System.out.println("不带时区的gmt-utc转换：：："+DateTimeUtil.gmtToUtc(
//				new Date()));
		System.out.println(simpleDateFormat.format(new Date()));
	}
}
