package com.fable.kscc.bussiness.controller.broadcastMeeting;

import com.fable.kscc.api.model.livebroadcast.FbsLiveBroadcast;
import com.fable.kscc.api.model.page.PageRequest;
import com.fable.kscc.api.model.page.PageResponse;
import com.fable.kscc.api.utils.StringUtil;
import com.fable.kscc.bussiness.service.broadcastMeeting.BroadcastMeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyh on 2017/8/7 0007.
 * 直播会议controller
 */
    @RequestMapping("/broadcastMeeting")
@Controller
public class BroadcastMeetController {
    @Autowired
    private BroadcastMeetService broadcastMeetService;

    /**
     * 获取直播会议列表
     */
    @RequestMapping("/getBroadcastMeetList")
    @ResponseBody
    public PageResponse<FbsLiveBroadcast> getBroadcastMeetList(@RequestBody PageRequest<Map<String,Object>> pageRequest) throws Exception {
        return broadcastMeetService.getBroadcastList(pageRequest);
    }

    /**
     * 修改直播会议列表
     */
    @RequestMapping("/editBroadcastMeet")
    @ResponseBody
    public Map<String,Object> editBroadcastMeet(@RequestBody FbsLiveBroadcast fbsLiveBroadcast){
        return broadcastMeetService.editBroadcastMeet(fbsLiveBroadcast);
    }
    
    /**
     * 获取直播会议列表
     */
    @RequestMapping("/getBroadcastForSchedule/{hospitalid}")
    @ResponseBody
    public List<Map<String,Object>> getBroadcastForSchedule(@PathVariable String hospitalid) throws Exception {
    	Map<String,String> map = new HashMap<String,String>();
    	if(StringUtil.isNotEmpty(hospitalid)&&!"0".equals(hospitalid))
    	{
    		map.put("hospitalid", hospitalid);
    	}
        return broadcastMeetService.getBroadcastForSchedule(map);
    }
}
