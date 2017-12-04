package com.fable.kscc.bussiness.service.uploadfile;

import java.math.BigDecimal;

public class BigDecimalUtil {
	static final int location = 10; // 小数点后位数
	// 加法 返回 num1+num2

	public static double add(double num1, double num2) {
		BigDecimal b1 = new BigDecimal(num1);
		BigDecimal b2 = new BigDecimal(num2);
		return b1.add(b2).doubleValue();
	}

	// 减法 返回 num1-num2
	public double sub(double num1, double num2) {
		BigDecimal b1 = new BigDecimal(num1);
		BigDecimal b2 = new BigDecimal(num2);
		return b1.subtract(b2).doubleValue();
	}

	// 乘法 返回 num1*num2
	public double mul(double num1, double num2) {
		BigDecimal b1 = new BigDecimal(num1);
		BigDecimal b2 = new BigDecimal(num2);
		return b1.multiply(b2).doubleValue();
	}

	// 除法 返回num1/num2 自定义小数点后位数
	public static double div(double num1, double num2) {
		BigDecimal b1 = new BigDecimal(num1);
		BigDecimal b2 = new BigDecimal(num2);
		return b1.divide(b2, location, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
