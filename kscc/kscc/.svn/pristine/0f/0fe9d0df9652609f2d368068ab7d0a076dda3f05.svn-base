package com.fable.kscc.bussiness.mapper.livebroadapprove;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fable.kscc.api.model.livebroadcast.FbsLiveBroadcast;
import com.fable.kscc.api.model.participant.FbsLiveParticipant;
import com.fable.kscc.api.utils.Page;

@Repository
public interface LiveBroadApproveMapper {
	/**
	 * 直播审批列表
	 * @param page
	 * @return
	 */
	public List<FbsLiveBroadcast> queryFbsLiveBroadList(Map<String,Object> map);
	/**
	 * 直播视频的总条数（分页）
	 * @param page
	 * @return
	 */
	public int queryFbLiveBroadListCount(Map<String,Object> map);
	/**
	 * 直播申请审批详情
	 * @param id
	 * @return
	 */
	public FbsLiveBroadcast queryFbsLiveBroadById(Integer id);
	/**
	 * 直播申请审批删除
	 * @param id
	 * @return
	 */
	public int deleteFbsLiveBroadById(Integer id);
	/**
	 * 直播申请审批修改
	 * @param id
	 * @return
	 */
	public int updateFbsLiveBroadById(FbsLiveBroadcast fbsLiveBroadcast);
	/**
	 * 更新直播审批状态
	 * @param id
	 * @return
	 */
	public int updateLiveApproveStatus(Integer id);
	
}
