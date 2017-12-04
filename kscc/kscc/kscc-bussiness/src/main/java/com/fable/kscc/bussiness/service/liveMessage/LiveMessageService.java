package com.fable.kscc.bussiness.service.liveMessage;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fable.kscc.api.model.message.FbsLiveMessage;
import com.fable.kscc.api.model.page.PageRequest;
import com.fable.kscc.api.model.page.PageResponse;

public interface LiveMessageService {
	/**
	 * 根据状态和发言人模糊查询消息信息
	 * @param pageRequest
	 * @return
	 */
	PageResponse<FbsLiveMessage> findMessageByStatus(HttpServletRequest request,PageRequest<Map<String,Object>> pageRequest);


	PageResponse<FbsLiveMessage> findMessageBySpeaker(HttpServletRequest request,PageRequest<Map<String,Object>> pageRequest);

	/**
	 * 批量已读
	 * @param id
	 * @return
	 */
	int updateLiveMessageStatus(String id);

	/**
	 * 批量删除
	 * @param id
	 * @return
	 */
	int deleteLiveMessageStatus(String id);

	/**
	 * 根据id读取单条消息
	 * @param id
	 * @return
	 */
	FbsLiveMessage findMessageById(Integer id);

	int queryExtendCount(Map<String,Object> param);

	void markExtendMessages(Map<String,Object> param);
}
