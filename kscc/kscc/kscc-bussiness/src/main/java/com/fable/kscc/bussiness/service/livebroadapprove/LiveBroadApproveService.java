package com.fable.kscc.bussiness.service.livebroadapprove;


import java.util.Map;

import com.fable.kscc.api.model.livebroadcast.FbsLiveBroadcast;
import com.fable.kscc.api.model.page.PageRequest;
import com.fable.kscc.api.model.page.PageResponse;

public interface LiveBroadApproveService {
	PageResponse<FbsLiveBroadcast> queryFbsLiveBroadList(PageRequest<Map<String,Object>> pageRequest);
	FbsLiveBroadcast queryFbsLiveBroadById(Integer id);
	int deleteFbsLiveBroadById(Integer id);
	Map<String,Object> updateFbsLiveBroadById(FbsLiveBroadcast fbsLiveBroadcast);
}
