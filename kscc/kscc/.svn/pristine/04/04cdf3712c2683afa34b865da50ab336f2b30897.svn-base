package com.fable.kscc.bussiness.controller.liveBroadApprove;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fable.kscc.bussiness.service.livebroadcast.LiveBroadCastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fable.kscc.api.model.livebroadcast.FbsLiveBroadcast;
import com.fable.kscc.bussiness.service.livebroadapprove.IExportService;
import com.fable.kscc.bussiness.service.livebroadapprove.LiveBroadApproveService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/kscc/wordExport")
public class WordExportController {
	@Autowired
	IExportService exportService;
	@Autowired
	LiveBroadApproveService liveBroadApproveService;
	@Autowired
	LiveBroadCastService liveBroadCastService;
	@RequestMapping(value = "/exportKsccWord" ,method = RequestMethod.POST)
	public void exportMonthWord(HttpServletRequest request ,HttpServletResponse response){
		final int id = Integer.parseInt(request.getParameter("id"));
		FbsLiveBroadcast bean = liveBroadApproveService.queryFbsLiveBroadById(id);
		//获取参与方
		Map<String,Object> map =  liveBroadCastService.getLiveDetail(new HashMap<String, Object>(){{put("liveId",id);}});
		List<Map<String, Object>> mapParticipants = (List<Map<String, Object>>) map.get("participants");
		request.getSession().setAttribute("participantList",mapParticipants);
		request.getSession().setAttribute("resultlist", bean);
		exportService.export2Word(request, response);
	}
	
}
