package com.fable.kscc.bussiness.service.liveControllerService;

import com.fable.kscc.api.model.operationLog.FbsLiveOperationLog;
import com.fable.kscc.api.model.page.ServiceResponse;

import javax.xml.ws.Service;
import java.util.List;
import java.util.Map;

/**
 * Created by Wanghairui on 2017/8/14.
 */
public interface IliveController {

    //正在进行的直播列表
    ServiceResponse getLiveList(Map<String,Object> param);
    //参与方列表状态显示
    ServiceResponse getParticipant(Map<String,Object> param);
    //    画面选看
    ServiceResponse choosePicture(Map<String,Object> param);
    //    切换主持人
    ServiceResponse switchHost(Map<String,Object> param);
    //    申请发言
    ServiceResponse applySpeak(Map<String,Object> param);
    //    选择发言人
    ServiceResponse chooseSpeaker(Map<String,Object> param);
    //    延长直播时间
    ServiceResponse extendTime(Map<String,Object> param);
    //    邀请参与方
    ServiceResponse inviteParticipant(Map<String,Object> param);
    //    删除参与方
    ServiceResponse deleteParticipant(Map<String,Object> param);
    //    画面合成
    ServiceResponse pictureSynthesis(Map<String,Object> param);
    //开启会议画面合成
    ServiceResponse pictureSynthesiss(Map<String,Object> param);
    //    静音
    ServiceResponse silence(Map<String,Object> param);
    //    哑音
    ServiceResponse mute(Map<String,Object> param);
    //    全场静音
    ServiceResponse allSilence(Map<String,Object> param);
    //    全场静音
    ServiceResponse allMute(Map<String,Object> param);
    //    结束直播
    ServiceResponse endLive(Map<String,Object> param);
    //    会场记录
    List<FbsLiveOperationLog> meetingRecord(Map<String,Object> param);
    //批量呼叫参与方
    ServiceResponse callParticipant(Map<String,Object> param);
    //获取视频会议列表
    ServiceResponse getLiveBroadCastList();

    ServiceResponse endConf();

    ServiceResponse sendScreenWord(Map<String,Object> param);

    ServiceResponse cancelPictureSynthesiss(Map<String,Object> param);

    ServiceResponse cancelChoosePicture(Map<String,Object> param);

    ServiceResponse getPictureSynthesiss(Map<String,Object> param);

    ServiceResponse getChoosePicture(Map<String,Object> param);

    ServiceResponse getOutMeeting(Map<String, Object> param);
    //视频开始或结束
    ServiceResponse startOrcancelVideo(Map<String, Object> param);

    ServiceResponse switchImage(Map<String, Object> param);

    ServiceResponse getSwitchMode(List<Map<String,String>> param);

    ServiceResponse getSwitchImage(List<Map<String,String>> param);
}
