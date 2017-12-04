package com.fable.kscc.bussiness.service.checkLiveService;

import com.alibaba.fastjson.JSON;
import com.fable.kscc.api.model.livebroadcast.FbsLiveBroadcast;
import com.fable.kscc.bussiness.mapper.livebroadcast.LiveBroadCastMapper;
import com.fable.kscc.bussiness.service.livebroadcast.LiveBroadCastService;
import com.fable.kscc.bussiness.websocket.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Wanghairui on 2017/8/24.
 */
@Service
public class CheckLiveService {

    @Autowired
    private LiveBroadCastMapper mapper;

    @Autowired
    private LiveBroadCastService service;

    @Autowired
    private Sender sender;

    private static final Logger logger = LoggerFactory.getLogger(CheckLiveService.class);

    private SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private  Map<String, Object> checkParam = new HashMap<String, Object>(){{ put("approvalStatus", "1");put("playStatus", "0");}};

    private  Map<String, Object> checkIsOutOfDateParam = new HashMap<String, Object>(){{ put("approvalStatus", "0");}};

    private  Map<String, Object> meetingIsEndParam = new HashMap<String, Object>(){{ put("playStatus", "1");}};

    private  ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    @PostConstruct
    public void  init(){
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    try{
                        Thread.sleep(5000);

                        //已经同意未开始直播的
                        List<FbsLiveBroadcast> fbsLiveBroadcasts = mapper.checkSql(checkParam);
                        for(FbsLiveBroadcast broadcast:fbsLiveBroadcasts){
                            Date startTime=sdf.parse(broadcast.getStartTime());
                            if (new Date().getTime() >= startTime.getTime()) {
                                service.startLive(String.valueOf(broadcast.getId()),0);
                            }
                        }

                        //已经申请未同意的
                        List<FbsLiveBroadcast> applyMeetings = mapper.checkSql(checkIsOutOfDateParam);
                        for(FbsLiveBroadcast broadcast:applyMeetings){
                            Date startTime=sdf.parse(broadcast.getStartTime());
                            if (new Date().getTime() >startTime.getTime()) {
                                service.cancelLive(String.valueOf(broadcast.getId()));
                            }
                        }

                        //正在直播的
                        List<FbsLiveBroadcast> livingMeeting = mapper.checkSql(meetingIsEndParam);
                        for(FbsLiveBroadcast broadcast:livingMeeting){
                            Date endTime=sdf.parse(broadcast.getEndTime());
                            if (new Date().getTime() >=endTime.getTime()) {
                                Map<String, Object> param = new HashMap<>();
                                param.put("id", broadcast.getId());
                                param.put("endTime", sdf.format(new Date()));
                                mapper.endLive(param);
                                Map<String, Object> message = new HashMap<>();
                                message.put("confAutoEnd"+broadcast.getConfId(), "");
                                sender.sendData(JSON.toJSONString(message),broadcast.getConfId());
                            }
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        logger.error("判断已批准并且未开始的会议是否需要自动开始,未批准会议是否超时未批准，到结束时间自动结束，发生异常",e);
                    }
                }
            }
        });
    }

}
