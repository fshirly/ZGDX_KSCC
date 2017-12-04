package com.fable.kscc.bussiness.service.liveoperationlog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fable.kscc.bussiness.mapper.livebroadcast.LiveBroadCastMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.kscc.api.model.operationLog.FbsLiveOperationLog;
import com.fable.kscc.api.model.page.PageRequest;
import com.fable.kscc.api.model.page.PageResponse;
import com.fable.kscc.bussiness.mapper.liveoperationlog.LiveOperationLogMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service("liveOperationLogServiceImpl")
public class LiveOperationLogServiceImpl implements LiveOperationLogService {
	@Autowired
	LiveOperationLogMapper liveOperationLogMapper;
	@Autowired
	private LiveBroadCastMapper broadCastMapper;

	@Override
	public PageResponse<FbsLiveOperationLog> queryFbsLiveOperationLogList(PageRequest<Map<String,Object>> pageRequest) {
		Map<String,Object> map=pageRequest.getParam();
        Page<FbsLiveOperationLog> result = PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());
        liveOperationLogMapper.queryFbsLiveOperationLogList(map);
        return PageResponse.wrap(result);
	}
	
	@Override
	public int insertLiveOperationLog(FbsLiveOperationLog fbsLiveOperationLog) {
		return liveOperationLogMapper.insertLiveOperationLog(fbsLiveOperationLog);
	}

	@Override
	public boolean insertApplySpeakLog(Map<String, Object> param) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		Map<String, Object> map = new HashMap<String, Object>();
		FbsLiveOperationLog logBean = new FbsLiveOperationLog();

		map.put("confId", param.get("confId").toString());
		int liveId = broadCastMapper.queryFbsLiveBroadcast(map).getId();

		String hospitalsName = param.get("hospitalsName").toString();
		logBean.setLiveId(liveId);
		logBean.setOperationTime(dateString);
		logBean.setOperationContent(hospitalsName+"申请发言成功");
		int num = liveOperationLogMapper.insertLiveOperationLog(logBean);
		if(num >0){
			return true;
		}else{
			return false;
		}
	}
}
