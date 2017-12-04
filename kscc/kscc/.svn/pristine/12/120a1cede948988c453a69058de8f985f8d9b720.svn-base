package com.fable.kscc.bussiness.controller.liveController;

import com.alibaba.fastjson.JSON;
import com.fable.kscc.api.model.operationLog.FbsLiveOperationLog;
import com.fable.kscc.api.model.page.ServiceResponse;
import com.fable.kscc.api.model.participant.FbsLiveParticipant;
import com.fable.kscc.bussiness.service.liveControllerService.IliveController;
import com.fable.kscc.bussiness.service.liveparticipant.LiveParticiPantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wanghairui on 2017/8/14.
 */
@Controller
@RequestMapping("/liveController")
public class LiveController {

    @Autowired
    private IliveController iliveController;

    @Autowired
    private LiveParticiPantService liveParticiPantService;

    @RequestMapping("/getLivingList")
    @ResponseBody
    public ServiceResponse getLivingList(@RequestBody Map<String,Object> param){
        return iliveController.getLiveList(param);
    }

    @RequestMapping("/choosePicture")
    @ResponseBody
    public ServiceResponse choosePicture(@RequestBody Map<String,Object> param){
        return iliveController.choosePicture(param);
    }

    /**
     * 选定发言人
     * @param param
     * @return
     */
    @RequestMapping("/chooseSpeaker")
    @ResponseBody
    public ServiceResponse chooseSpeaker(@RequestBody Map<String,Object> param){
        return iliveController.chooseSpeaker(param);
    }
    //开启终端画面自主合成
    @RequestMapping("/pictureSynthesis")
    @ResponseBody
    public ServiceResponse pictureSynthesis(@RequestBody Map<String,Object> param){
        return iliveController.pictureSynthesis(param);
    }
    //开启画面合成
    @RequestMapping("/pictureSynthesiss")
    @ResponseBody
    public ServiceResponse pictureSynthesiss(@RequestBody final Map<String,Object> param){
        return iliveController.pictureSynthesiss(param);
    }

    @RequestMapping("/allSilence")
    @ResponseBody
    public ServiceResponse allSilence(@RequestBody Map<String,Object> param){
        return iliveController.allSilence(param);
    }

    @RequestMapping("/allMute")
    @ResponseBody
    public ServiceResponse allMute(@RequestBody Map<String,Object> param){
        return iliveController.allMute(param);
    }

    @RequestMapping("/switchHost")
    @ResponseBody
    public ServiceResponse switchHost(@RequestBody Map<String,Object> param){
        return iliveController.switchHost(param);
    }

    @RequestMapping("/inviteParticipant")
    @ResponseBody
    public ServiceResponse inviteParticipant(@RequestBody Map<String,Object> param){
        return iliveController.inviteParticipant(param);
    }

    @RequestMapping("/silence")
    @ResponseBody
    public ServiceResponse silence(@RequestBody Map<String,Object> param){
        return iliveController.silence(param);
    }

    @RequestMapping("/endLive")
    @ResponseBody
    public ServiceResponse endLive(@RequestBody Map<String,Object> param){
        return iliveController.endLive(param);
    }

    @RequestMapping("/mute")
    @ResponseBody
    public ServiceResponse mute(@RequestBody Map<String,Object> param){
//    	Map<String,Object> param = new HashMap<>();
    	return iliveController.mute(param);
    }
    //删除参与方
    @RequestMapping("/deleteParticipant")
    @ResponseBody
    public ServiceResponse deleteParticipant(@RequestBody Map<String,Object> param){
    	return iliveController.deleteParticipant(param);
    }

    @RequestMapping("/extendTime")
    @ResponseBody
    public ServiceResponse extendTime(@RequestBody Map<String,Object> param){
    	return iliveController.extendTime(param);
    }

    @RequestMapping("/meetingRecord")
    @ResponseBody
    public List<FbsLiveOperationLog> meetingRecord(@RequestBody Map<String,Object> param){
    	return iliveController.meetingRecord(param);
    }

    @RequestMapping("/getParticipant")
    @ResponseBody
    public FbsLiveParticipant getMeetingRoll(@RequestBody  Map<String,Object> map){
        return liveParticiPantService.queryParticipantHost(map);
    }

    @RequestMapping("/callParticipant")
    @ResponseBody
    public ServiceResponse callParticipant(@RequestBody Map<String,Object> param){
    	return iliveController.callParticipant(param);
    }

    //获取会议列表
    @RequestMapping("/getLiveBroadCastList")
    @ResponseBody
    public ServiceResponse getLiveBroadCastList(){
    	return iliveController.getLiveBroadCastList();
    }

    //结束所有会议
    @RequestMapping("/endConf")
    @ResponseBody
    public ServiceResponse endConf(){
        return iliveController.endConf();
    }

    //发弹幕
    @RequestMapping("/sendScreenWord")
    @ResponseBody
    public ServiceResponse sendScreenWord(@RequestBody Map<String,Object> param){
        return iliveController.sendScreenWord(param);
    }

    /**
     *
     * @param param {confId:'???'}
     * @return ServiceResponse
     */
    @RequestMapping("/cancelPictureSynthesiss")
    @ResponseBody
    public ServiceResponse cancelPictureSynthesiss(@RequestBody Map<String,Object> param){
        return iliveController.cancelPictureSynthesiss(param);
    }

    /**
     *
     * @param param {confId:'???',mtId:"???"}
     * @return ServiceResponse
     */
    @RequestMapping("/cancelChoosePicture")
    @ResponseBody
    public ServiceResponse cancelChoosePicture(@RequestBody Map<String,Object> param){
        return iliveController.cancelChoosePicture(param);
    }

    /**
     *
     * @param param {confId:'???'}
     * @return ServiceResponse
     */
    @RequestMapping("/getPictureSynthesiss")
    @ResponseBody
    public ServiceResponse getPictureSynthesiss(@RequestBody Map<String,Object> param){
        return iliveController.getPictureSynthesiss(param);
    }

    /**
     *
     * @param param {confId:'???'}
     * @return ServiceResponse
     */
    @RequestMapping("/getChoosePicture")
    @ResponseBody
    public ServiceResponse getChoosePicture(@RequestBody Map<String,Object> param){
        return iliveController.getChoosePicture(param);
    }

    /**
     * 退出直播间
     * @param param
     * @return
     */
    @RequestMapping("/getOutMeeting")
    @ResponseBody
    public ServiceResponse getOutMeeting(@RequestBody Map<String,Object> param){
        return iliveController.getOutMeeting(param);
    }

    /**
     * 开启和关闭录像
     * @param param
     * @return
     */
    @RequestMapping("/startOrcancelVideo")
    @ResponseBody
    public ServiceResponse startOrcancelVideo(@RequestBody Map<String,Object> param){
        return iliveController.startOrcancelVideo(param);
    }

    /**
     * hd和spies画面切换
     * @param param
     * @return
     */
    @RequestMapping("/switchImage")
    @ResponseBody
    public ServiceResponse switchImage(@RequestBody Map<String,Object> param){
        return iliveController.switchImage(param);
    }

    /**
     * 获取录像开启或者暂停
     * @param param
     * @return
     */
    @RequestMapping("/getSwitchMode")
    @ResponseBody
    public ServiceResponse getSwitchMode(@RequestBody List<Map<String,String>> param){
        return iliveController.getSwitchMode(param);
    }

    /**
     * 获取hd或者spies
     * @param param
     * @return
     */
    @RequestMapping("/getSwitchImage")
    @ResponseBody
    public ServiceResponse getSwitchImage(@RequestBody List<Map<String,String>> param){
        return iliveController.getSwitchImage(param);
    }

    @RequestMapping("/getBmpImageInfo")
    @ResponseBody
    public ServiceResponse getBmpImageInfo(@RequestBody Map<String,Object> param){
        return iliveController.getBmpImageInfo(param);
    }
}
