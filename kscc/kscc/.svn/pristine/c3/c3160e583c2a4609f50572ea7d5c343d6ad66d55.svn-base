package com.fable.kscc.bussiness.controller.liveoperationlog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fable.kscc.api.model.livebroadcast.FbsLiveBroadcast;
import com.fable.kscc.api.model.operationLog.FbsLiveOperationLog;
import com.fable.kscc.api.model.page.PageRequest;
import com.fable.kscc.api.model.page.PageResponse;
import com.fable.kscc.api.utils.Page;
import com.fable.kscc.api.utils.PageUtil;
import com.fable.kscc.bussiness.service.liveoperationlog.LiveOperationLogService;

@Controller
@RequestMapping("/kscc/liveOperationLog")
public class LiveOperationLogController {
	@Autowired
	LiveOperationLogService liveOperationLogService;
	private final Logger logger = LoggerFactory.getLogger(LiveOperationLogController.class);
	
	@RequestMapping("/liveFbsLiveParticiList")
	@ResponseBody
	public PageResponse<FbsLiveOperationLog> liveFbsOperationLogList(@RequestBody PageRequest<Map<String,Object>> pageRequest)
		throws Exception{
		logger.info("获取操作日志信息开始");
		return liveOperationLogService.queryFbsLiveOperationLogList(pageRequest);
	}
	
	/**
	 * 新增操作记录
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insertLiveOperationLog")
	@ResponseBody
	public boolean insertLiveOperationLog(FbsLiveOperationLog bean) throws Exception {
		int insertFlag = liveOperationLogService.insertLiveOperationLog(bean);
		if (insertFlag > 0) {
			return true;
		}
		return false;
	}

	@RequestMapping("/insertApplySpeakLog")
	@ResponseBody
	public boolean insertApplySpeakLog(@RequestBody Map<String,Object> param){
		return liveOperationLogService.insertApplySpeakLog(param);
	}


}
