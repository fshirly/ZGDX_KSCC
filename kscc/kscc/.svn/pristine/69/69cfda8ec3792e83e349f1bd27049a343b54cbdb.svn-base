package com.fable.kscc.bussiness.mapper.LiveMessage;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fable.kscc.api.model.message.FbsLiveMessage;
import com.fable.kscc.api.utils.Page;

@Repository
public interface LiveMessageMapper {

	/**
	 * 根据状态和发言人模糊查询消息信息
	 * @param map
	 * @return
	 */
	public List<FbsLiveMessage> findMessageByStatus(@Param("map") Map<String,Object> map);

	/**
	 * 查看总页数
	 * @param map
	 * @return
	 */
	public int queryListCount(Map<String,Object> map);

	/**
	 * 批量已读
	 * @param num
	 * @return
	 */
	public int updateLiveMessageStatus(int[] num);

	/**
	 * 批量删除
	 * @param num
	 * @return
	 */
	public int deleteLiveMessageStatus(int[] num);

	/**
	 * 根据id读取单条消息
	 * @param id
	 * @return
	 */
	public FbsLiveMessage findMessageById(@Param("id")Integer id);
	/**
	 * 新增消息信息
	 * @param fbsLiveMessage
	 * @return
	 */
	public int insertLiveMessage(FbsLiveMessage fbsLiveMessage);

	public int queryExtendCount(Map<String,Object> map);

}
