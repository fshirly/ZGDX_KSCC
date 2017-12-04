package com.fable.kscc.bussiness.mapper.liveoperationlog;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fable.kscc.api.model.livebroadcast.FbsLiveBroadcast;
import com.fable.kscc.api.model.operationLog.FbsLiveOperationLog;
import com.fable.kscc.api.utils.Page;

public interface LiveOperationLogMapper {
	/**
	 * 直播操作记录查询
	 * @param map
	 * @return
	 */
	public List<FbsLiveOperationLog> queryFbsLiveOperationLogList(Map<String,Object> map);
	/**
	 * 直播操作记录总数
	 * @param page
	 * @return
	 */
	public int queryFbsLiveOperationLogCount(Page<FbsLiveOperationLog> page);
	/**
	 * 插入直播操作记录
	 * @param fbsLiveOperationLog
	 * @return
	 */
	public int insertLiveOperationLog(FbsLiveOperationLog fbsLiveOperationLog);
	
	/**
	 * 查看当前直播间会场记录
	 * @param param
	 * @return
	 */
	public List<FbsLiveOperationLog> meetingRecord(@Param("param") Map<String,Object> param);
	
}
