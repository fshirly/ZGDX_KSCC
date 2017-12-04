package com.fable.kscc.bussiness.controller.livebroadcast;

import com.fable.kscc.api.model.liveHomePage.FbsLiveHomepage;
import com.fable.kscc.api.model.livebroadcast.FbsLiveBroadcast;
import com.fable.kscc.api.model.page.PageRequest;
import com.fable.kscc.api.model.page.PageResponse;
import com.fable.kscc.api.model.page.ResultKit;
import com.fable.kscc.api.model.page.ServiceResponse;
import com.fable.kscc.api.model.user.FbsUser;
import com.fable.kscc.bussiness.service.livebroadcast.LiveBroadCastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/liveBroadCastController")
public class LiveBroadCastController {

	@Autowired
	private LiveBroadCastService liveBroadCastService;

	/**
	 * 创建直播申请
	 * @param broadcast
	 * @return
	 */
	@RequestMapping(value="/createLiveApplications" , method=RequestMethod.POST )
	@ResponseBody
	public ServiceResponse createLiveApplications(@RequestBody FbsLiveBroadcast broadcast,HttpServletRequest request){
		HttpSession session = request.getSession();
		FbsUser ksUser = (FbsUser) session.getAttribute("ksUser");
		Integer userid = ksUser.getId();
		broadcast.setUserId(userid);
		return liveBroadCastService.createLiveApplications(broadcast);
	}


	//我的直播
	@RequestMapping("/myLiveBroadcast")
	@ResponseBody
	public ServiceResponse myLiveBroadcast(@RequestBody Map<String,Object> map){
		return liveBroadCastService.getLiveBroadCast(map);
	}
	//我申请的直播
	@RequestMapping("/queryLiveBroadcast")
	@ResponseBody
	public ServiceResponse queryLiveBroadcast(@RequestBody Map<String,Object> map){
		return liveBroadCastService.applyBroadCast(map);
	}

	@RequestMapping("/getUserId")
	@ResponseBody
	public FbsUser getUserId(HttpServletRequest request){
 			FbsUser user=(FbsUser)request.getSession().getAttribute("ksUser");
		return user;
	}

	@RequestMapping("/inviteLive")
	@ResponseBody
	public ServiceResponse inviteLive(@RequestBody Map<String,Object> map) {
		return liveBroadCastService.inviteLive(map);
	}

	@RequestMapping("/getLiveBroadcast")
	@ResponseBody
	public PageResponse<FbsLiveBroadcast> getLiveBroadcast(@RequestBody PageRequest<Map<String,Object>> pageRequest) {
		return liveBroadCastService.getAllHistoryBroadCast(pageRequest);
	}

	@RequestMapping("/agreeInviteOrNot")
	@ResponseBody
	public int agreeInviteOrNot(@RequestBody Map<String,Object> param){
		return liveBroadCastService.agreeInviteOrNot(param);
	}

	/**
	 * 单个直播详情，包含参与方
	 * @param id 创建的会议id
	 * @return 会议详情
	 */
	@RequestMapping("/getLiveDetail/{id}")
	@ResponseBody
	public Map<String,Object> getLiveDetail(@PathVariable final String id){
		return liveBroadCastService.getLiveDetail(new HashMap<String ,Object>(){{put("liveId",id);}});
	}
	/**
	 * 获取直播列表(参与方按钮调用)
	 * @param id
	 * @return
	 */
	@RequestMapping("/getLiveDetailTwo/{id}")
	@ResponseBody
	public Map<String,Object> getLiveDetailTwo(@PathVariable final String id){
		return liveBroadCastService.getLiveDetailTwo(new HashMap<String ,Object>(){{put("liveId",id);}});
	}
	@RequestMapping("/startLive/{id}")
	@ResponseBody
	public ServiceResponse startLive(@PathVariable final String id){
		return liveBroadCastService.startLive(id,1);
	}

    @RequestMapping("/cancelLive/{id}")
    @ResponseBody
    public Map<String,Object> cancelLive(@PathVariable String id){
		return liveBroadCastService.cancelLive(id);
	}

	@RequestMapping("/agreeOrNot")
	@ResponseBody
	public int agreeOrNot(@RequestBody Map<String,Object> param){
		return liveBroadCastService.agreeInviteOrNot(param);
	}

	@RequestMapping("/modifyBroadcast")
	@ResponseBody
	public ServiceResponse  modifyBroadcast(@RequestBody Map<String,Object> param,HttpServletRequest request){
		HttpSession session = request.getSession();
		FbsUser ksUser = (FbsUser) session.getAttribute("ksUser");
		param.put("user_id", ksUser.getId());
		try {
		    return liveBroadCastService.modifyLiveBroadcast(param);
		} catch ( final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/getMts/{id}/{userId}/{roleCode}")
	@ResponseBody
	public ServiceResponse getMts(@PathVariable  String id,@PathVariable String userId,@PathVariable String roleCode){
		 return liveBroadCastService.getMts(id,userId,roleCode);
	}


	/**
	 * 更新当前直播间的结束时间(延时)
	 * @param param
	 * @return
	 */
	@RequestMapping("/updateEndTime")
	@ResponseBody
	public String updateEndTime(@RequestBody Map<String,Object> param){
		return liveBroadCastService.updateEndTime(param);
	}

	/**
	 * 获取当前直播间结束时间
	 * @param param
	 * @return
	 */
	@RequestMapping("/searchEndTime")
	@ResponseBody
	public String searchEndTime(@RequestBody Map<String,Object> param){
		return liveBroadCastService.searchEndTime(param);
	}

	/**
	 *获取首页设置图片下拉框的值
	 * @return FbsLiveBroadcast
	 */
	@RequestMapping("/getSelectBroadcast")
	@ResponseBody
	public ServiceResponse getSelectBroadcast(){
		return liveBroadCastService.getSelectBroadcast();
	}

	/**
	 *添加首页轮播图片
	 * @return int
	 */
	@RequestMapping("/addHomePicture")
	@ResponseBody
	public ServiceResponse addHomePicture(@RequestBody  FbsLiveHomepage homepage){
		return liveBroadCastService.addHomePicture(homepage);
	}

	/**
	 *删除首页轮播图片
	 * @return int
	 */
	@RequestMapping("/deleteHomePicture")
	@ResponseBody
	public ServiceResponse deleteHomePicture(@RequestBody  FbsLiveHomepage homepage){
		return liveBroadCastService.deleteHomePicture(homepage);
	}

	@RequestMapping("/selectHomePage")
	@ResponseBody
	public ServiceResponse selectHomePage(){
		return liveBroadCastService.selectHomePage();
	}


	@RequestMapping("/getMeetingInfo")
	@ResponseBody
	public ServiceResponse getMeetingInfo(@RequestBody Map<String,Object> param){
		return liveBroadCastService.getMeetingInfo(param);
	}

	@RequestMapping("/getCurrentParticipant")
	@ResponseBody
	public ServiceResponse getCurrentParticipant(@RequestBody Map<String,Object> param,HttpServletRequest request){
		List<Map<String,Object>> paramMap = liveBroadCastService.getCurrentParticipant(param);
		FbsUser user=(FbsUser)request.getSession().getAttribute("ksUser");
		final int loginUserid = user.getId();
		for (Map<String,Object> map : paramMap){
			map.put("loginUserid",loginUserid);
		}
		return ResultKit.serviceResponse(paramMap);
	}

}
