package com.fable.kscc.api.utils;
import javax.servlet.http.HttpServletRequest;


/**
 * 封装分页参数
 * @author duy
 */
public class PageUtil {

	public static Page<? extends Object> getRequestPage(HttpServletRequest request,Page<? extends Object> page) {
		String currentPage = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		System.out.println("rows:" + pageSize);
		page.setPageCurrent(StringUtil.isNotEmpty(currentPage) ? Integer.parseInt(currentPage) : page.getPageCurrent());
		page.setPageSize(StringUtil.isNotEmpty(pageSize) ? Integer.parseInt(pageSize) : page.getPageSize());
		return page;
	}
}
