package com.fable.kscc.bussiness.service.livebroadapprove;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fable.kscc.api.model.livebroadcast.FbsLiveBroadcast;

public interface IExportService {
	void export2Word(HttpServletRequest request ,HttpServletResponse response);
	String getFieldValue(FbsLiveBroadcast obj, String field);
}
