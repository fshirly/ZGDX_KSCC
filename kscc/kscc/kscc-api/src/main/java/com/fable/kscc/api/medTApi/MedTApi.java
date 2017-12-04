package com.fable.kscc.api.medTApi;

import com.fable.kscc.api.utils.Constants;
import com.fable.kscc.api.utils.HttpUtils;
import com.fable.kscc.api.utils.ThreadPoolUtils;
import com.fable.kscc.api.utils.XmlGenerator;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Created by Wanghairui on 2017/10/10.
 */
@Component
public class MedTApi {

    public static Logger logger = LoggerFactory.getLogger(MedTApi.class);

    private  Map<String, Boolean> flag = new HashMap<>();

    public static void main(String[] args) {
       final Map<String, String> login = new HashMap<>();

//        login.put("ip", "140.207.48.50");
//        login.put("port","50080");


        login.put("ip", "59.45.90.59");
        login.put("port", "50080");
        login.put("username", "admin");
        login.put("password", "admin123");
        login.put("hospitalId", "1");
        login.put("confId", "50080");
        login.put("startTime", "2017-11-09 13:10:22");
        login.put("endTime", "2017-11-09 13:15:02");
        login.put("mode", "start");
        final MedTApi api = new MedTApi();
        String authenticationId=api.getAuthenticationId(login);
        System.out.println(authenticationId);
        login.put("authenticationid", authenticationId);
        XmlGenerator.generateRequestRoot(login);
        api.Login(login);
        api.getDisks(login);
       /* String xmlResponse = HttpUtils.httpPostByXml("http://180.168.216.178:50080/"+Constants.MedT100.getChnRecCfg,"<contentroot><authenticationinfo type=\"7.0\"><username>admin</username><password>NjJhMWFjOGU5MTlkODZhMzJkN2U3M2M5ZTk5NWE4YjE=</password><authenticationid>56903800832278</authenticationid></authenticationinfo><GetChnRecCfgReq><ChnIDStart>3</ChnIDStart><ChnIDEnd>3</ChnIDEnd></GetChnRecCfgReq></contentroot>");
        System.out.println(xmlResponse);*/
    }

    /**
     *
     * @param param
     * @return 鉴权id
     * @description 鉴权
     */
    public String getAuthenticationId(Map<String,String> param){
        String response=HttpUtils.httpPostByNullXml(getSocketAddress(param)+Constants.MedT100.authentication);
        System.out.println(param.get("hospitalName")+"鉴权：：："+response);
        logger.info(param.get("hospitalName")+"鉴权：：："+response);
        try {
            if(response!=null){
                Document doc = DocumentHelper.parseText(response);
                return doc.getRootElement().elementText("Authenticationid");
            }
           return null;
        } catch (DocumentException e) {
            logger.error(param.get("hospitalName")+"的编解码器鉴权发生错误",e);
            return null;
        }
    }

    /**
     *
     * @param param
     * @description 登陆
     */
    public boolean Login(Map<String,String> param){

        String params = XmlGenerator.generateLoginParam(param);
        String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param) + Constants.MedT100.login, params);
        try {
            Document doc = DocumentHelper.parseText(xmlResponse);
            String response=doc.getRootElement().elementText("statusstring");

            String statusString = doc.getRootElement().elementText("substatusstring");
            if (!"success".equals(response)) {
                logger.info(param.get("hospitalName")+"的编解码器登陆失败："+statusString);
                return false;
            }
            return true;
        } catch (DocumentException e) {
            logger.error(param.get("hospitalName")+"的编解码器登陆发生错误",e);
            return false;
        }
    }

    /**
     *
     * @param param
     * @description 心跳
     */
    public  void heartBeat(Map<String,String> param){

        String params = XmlGenerator.generateKeepHeartBeatParam(param);
        try {
            String response = HttpUtils.httpPostByXml(getSocketAddress(param)+Constants.MedT100.heartBeat, params);
            Document doc = DocumentHelper.parseText(response);
            String stateResp = doc.getRootElement().getName();
            if (!"StateResp".equals(stateResp)) {
                logger.error(param.get("hospitalId")+"的编解码器心跳");
            }
        }catch (Exception e) {
            logger.error("心跳出错",e);
            System.out.println(param.get("hospitalName")+"chinese:心跳骤停 heartBeat stopping。。。。");
            MedRunnableMap.map.get(param.get("id")).setFlag(false);//停止此编解码心跳线程
            initMedT100(param);
        }
    }

    /**
     *
     * @param param
     * @return 完成与否
     * @description 录像文件备份进程
     */
    public String Mp4BackUpProgress(Map<String,String> param){

        String params =XmlGenerator.generateHaveNotParam(param);

        String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param)+Constants.MedT100.mp4BackupProgress,params);
        System.out.println(xmlResponse);
        try {
            Document document = DocumentHelper.parseText(xmlResponse);
            Element root = document.getRootElement();
            String state = root.elementText("State");
            System.out.println("whole task state:"+state);
            System.out.println("backup progress ："+root.element("ChnStatList").element("ChnStat").elementText("Progress"));
            logger.info(root.element("ChnStatList").element("ChnStat").elementText("Progress"));
            if("2".equals(state)){
                String Status=root.element("ChnStatList").element("ChnStat").elementText("Status");
                if("2".equals(Status)){
                    return "3";
                }
            }
            return state;//0：空闲 1：运行中 2：已完成 3：备份失败
        } catch (DocumentException e) {
            System.out.println(param.get("hospitalId")+"medt100 query backup progress error");
            logger.error(param.get("hospitalId")+"medt100 query backup progress error",e);
            return "3";
        }
    }

    /**
     *
     * @param param
     * @return 成功与否
     * @description 平台和编解码器之间输出画面通道切换
     */
    public String viewSelfOrOther(Map<String,String> param, int flag){
        String params = XmlGenerator.generateSetVmtParam(param,flag);
        System.out.println(params);
        String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param) + Constants.MedT100.viewSelf, params);
        logger.debug("viewSelfOrOther:"+xmlResponse);
        try {
            Document doc = DocumentHelper.parseText(xmlResponse);

            String response=doc.getRootElement().elementText("statusstring");

            String statusString = doc.getRootElement().elementText("substatusstring");

            if (!"success".equals(response)) {
                logger.info(param.get("hospitalName")+"的编解码器设置DVI（4选2-1）输出到DVI2："+statusString);
                return "fail";
            }
            return "success";
        } catch (DocumentException e) {
            logger.error(param.get("hospitalName")+"的编解码器登陆发生错误",e);
        }
        return "fail";
    }

    /**
     *
     * @param param
     * @return 文件目录
     * @description 查询备份好的MP4
     * 文件
     */
    public String Mp4FileQuery(Map<String,String> param){
        String params = XmlGenerator.generateMp4FileQueryParam(param);
        System.out.println(params);
        String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param) + Constants.MedT100.mp4FileQuery, params);
        System.out.println(xmlResponse);
        String srcPath=null;
        try {
            Document document = DocumentHelper.parseText(xmlResponse);
            Element MP4List=document.getRootElement().element("MP4List");
            String fileNum = MP4List.attributeValue("num");
            if("0".equals(fileNum)){
                return null;
            }
            srcPath= MP4List.element("MP4Info").elementText("TitlePath");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return srcPath;
    }

    /**
     *
     * @param param
     * @description 让编解码器和服务器时间同步
     */
    public void SetSysTimeInfo(Map<String,String> param){
        String params = XmlGenerator.generateSetSysTimeInfoParam(param);
        String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param) + Constants.MedT100.setSysTimeInfo, params);
        try {
            Document doc = DocumentHelper.parseText(xmlResponse);
            String response=doc.getRootElement().elementText("statusstring");

            String statusString = doc.getRootElement().elementText("substatusstring");
            if (!"success".equals(response)) {
                logger.info(param.get("hospitalName")+"的编解码器时间同步："+statusString);
            }
        } catch (DocumentException e) {
            logger.error(param.get("hospitalName")+"的编解码器时间同步",e);
        }
    }

    /**
     *
     * @param param
     * @return 成功与否
     * @description 录像开始备份
     */
    public String Mp4BackupStart(Map<String,String> param){
        String params = XmlGenerator.generateMp4BackupStartParam(param);
        String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param)+Constants.MedT100.mp4BackupStart,params);
        System.out.println(xmlResponse);
        String response;
        try {
            Document doc = DocumentHelper.parseText(xmlResponse);
            response=doc.getRootElement().elementText("statusstring");
            String statuscode=doc.getRootElement().elementText("statuscode");
            String statusString = doc.getRootElement().elementText("substatusstring");
            if (!"success".equals(response)) {
                if("1001".equals(statuscode)){
                    System.out.println(param.get("ip")+":"+param.get("port")+"       being backup");
                    return "failure";
                }
                logger.info(param.get("hospitalName")+"machine  backup fail："+statusString);
            }
            return response;
        } catch (DocumentException e) {
            logger.error(param.get("hospitalName")+"start back up fail Exception",e);
            e.printStackTrace();
            return "failure";
        }
    }

    /**
     *
     * @param param
     * @return String
     * @description   结束备份
     */
    public String Mp4BackupStop(Map<String,String> param){
        String params = XmlGenerator.generateHaveNotParam(param);
        String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param)+Constants.MedT100.mp4BackupStop,params);
        System.out.println(xmlResponse);
        return xmlResponse;
    }

    /**
     *
     * @param param
     * @return 设置通道参数
     * @description 获取编解码通道录像配置
     */
    public String getChnRecCfg(Map<String,String> param){
        String params = XmlGenerator.generateGetChnRecCfgParam(param);
        String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param)+Constants.MedT100.getChnRecCfg,params);
        System.out.println("getVideoMesssage:::::"+xmlResponse);
        logger.debug("getChnRecCfg:"+xmlResponse+"==============");
        String mode = null;
        try {
            Element elementVoid = (Element)(DocumentHelper.parseText(xmlResponse).getRootElement().element("CfgList").elements().get(0));
            mode = elementVoid.elementText("Mode");
            return mode;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "获取编解码器录像通道失败";
    }

    /**
     *
     * @param param
     * @return 成功与否
     * @description 开始录像或者停止录像
     */
    public String setChnRecCfg(Map<String,String> param){
        param.put("CfgList", getChnRecCfglist(param));
        String params = XmlGenerator.generateSetChnRecCfgParam(param);
        System.out.println("startOrStopVideo::::"+params);
        String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param)+Constants.MedT100.setChnRecCfg,params);
        System.out.println("startOrStopVideo::::"+xmlResponse);
        logger.debug("setChnRecCfg:"+xmlResponse+"==============");
        String response;
        try {
            Document doc = DocumentHelper.parseText(xmlResponse);
            response=doc.getRootElement().elementText("statusstring");
            if ("success".equals(response)) {
                logger.info(param.get("hospitalName")+"录像通道配置成功！");
                return "success";
            }
                return "fail";
        } catch (DocumentException e) {
            logger.error(param.get("hospitalName")+"录像通道配置失败",e);
            return "fail";
        }
    }

    public String getChnRecCfglist(Map<String,String> param){
        String params = XmlGenerator.generateGetChnRecCfgParam(param);
        String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param)+Constants.MedT100.getChnRecCfg,params);
        try {
            Document doc = DocumentHelper.parseText(xmlResponse);
            Element CfgList=doc.getRootElement().element("CfgList");
            Element cfg = CfgList.element("Cfg");
            cfg.remove(cfg.element("TimeList"));
            return CfgList.asXML();
        }
        catch (Exception e){
            e.printStackTrace();
            return "获取通道配置发生异常";
        }
    }
    /**
     *
     * @param param
     * @return 成功与否
     * @description 编解码器上的文件删除
     */
    public Map<String,String>  mp4FilesDelete(Map<String,String> param){
        String params = XmlGenerator.generateMp4FilesDeleteParam(param);
        System.out.println("params："+params);
        String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param)+Constants.MedT100.mp4FilesDelete,params);
        System.out.println(xmlResponse);
        String response;
        try {
            Document doc = DocumentHelper.parseText(xmlResponse);
            response=doc.getRootElement().elementText("statusstring");
            final String message = doc.getRootElement().elementText("substatusstring");
            if ("success".equals(response)) {
                logger.info("删除备份录像成功！");
                return new HashMap<String,String>(){{put("success","1");}};
            }
            else {
                return new HashMap<String,String>(){{put("success","0");put("message",message);}};
            }
        } catch (final  DocumentException e) {
            logger.error("删除备份录像失败",e);
            return new HashMap<String,String>(){{put("success","0");put("message",e.getMessage());}};
        }

    }

    /**
     *
     * @param param
     * @return 所有预案信息
     * @description   获取所有预案信息
     */
    public String getAllVideoAdjustScheme(Map<String,String> param){
        String params = XmlGenerator.generateHaveNotParam(param);
        String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param)+Constants.MedT100.getAllVideoAdjustScheme,params);
        return xmlResponse;
    }

    /**
     *
     * @param param
     * @return 所有通道对应预案信息
     * @description   获取所有通道对应预案信息
     */
    public String getAllChnScheme(Map<String,String> param){
        String params = XmlGenerator.generateHaveNotParam(param);
        String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param)+Constants.MedT100.getAllChnScheme,params);
        System.out.println("getImageMessage::::::"+xmlResponse);
        String SchID = null;
        try {
            Element element = (Element)(DocumentHelper.parseText(xmlResponse).getRootElement().element("ChnSchemeList").elements().get(5));
            SchID = element.elementText("SchID");
            return SchID;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "获取所有通道对应预案信息失败";

    }

    /**
     *
     * @param param
     * @return 成功与否
     * @description   hd/spies切换
     */
    public String setChnScheme(Map<String,String> param){
        String params = XmlGenerator.generateSetChnSchemeParam(param);
        String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param) + Constants.MedT100.setChnScheme, params);
        System.out.println("hd/spies:::::::::"+xmlResponse);
        String response;
        try {
            Document doc = DocumentHelper.parseText(xmlResponse);
            response=doc.getRootElement().elementText("statusstring");

            if ("success".equals(response)) {
                logger.info(param.get("hospitalName")+"图像切换设置成功！");
                return "success";
            }
            return "fail";
        } catch (DocumentException e) {
            logger.error(param.get("hospitalName")+"图像切换设置失败",e);
        }
        return "fail";
    }

    //generateGetMpuVidEncParam
    /**
     *
     * @param param
     * @return string
     * @description   运维部分，获取编解码参数
     */
    public Map<String,Object> getMpuVidEnc(Map<String,String> param){
        Map<String,Object> map = new HashMap<>();
        try {
            String params = XmlGenerator.generateGetMpuVidEncParam(param);
            String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param) + Constants.MedT100.getMpuVidEncParam, params);
            System.out.println(xmlResponse);
            Element root = null;
            Document document = DocumentHelper.parseText(xmlResponse);
            root = document.getRootElement();
            String width = root.element("Resolution").elementText("Width");
            String height = root.element("Resolution").elementText("Height");
            System.out.println(width + "*" + height);
            map.put("success", "1");
            map.put("Width",width);
            map.put("Height",height);
            //帧率
            String frame = root.elementText("FrameRate");
            map.put("FrameRate",frame);
            System.out.println("FrameRate" +frame );
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<String,Object>(){{put("success", "0");}};
        }
        return map;
    }
    /**
     *
     * @param param
     * @return String
     * @description   运维部分，获取设备信息
     */
    public String getSysDevInfo(Map<String,String> param){
        String params = XmlGenerator.generateHaveNotParam(param);
        String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param)+Constants.MedT100.getSysDevInfo,params);
        System.out.println(xmlResponse);
        return xmlResponse;
    }

    /**
     *
     * @param param
     * @return
     * @description 运维部分，获取当前硬盘信息
     */
    public Map<String,Object> getDisks(Map<String,String> param){
        Map<String,Object> map = new HashMap<>();
        try {
            String params = XmlGenerator.generateHaveNotParam(param);
            String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param)+Constants.MedT100.getDisks,params);
            System.out.println("getDiskMessage:"+xmlResponse);
            Element element = (Element)(DocumentHelper.parseText(xmlResponse).getRootElement().element("DiskList").elements().get(0));
            map.put("success", "1");
            map.put("Id",element.elementText("Id"));
            map.put("Capacity",element.elementText("Capacity"));
            map.put("Status",element.elementText("Status"));
            map.put("Attr",element.elementText("Attr"));
            map.put("Type",element.elementText("Type"));
            map.put("Free",element.elementText("Free"));
            map.put("GroupID",element.elementText("GroupID"));
            map.put("SmartState",element.elementText("SmartState"));
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<String,Object>(){{put("success", "0");}};
        }
        System.out.println(map);
        return map;
    }

    /**
     *
     * @param param
     * @return
     * @description 运维部分，获取磁盘信息
     */
    public Map<String,Object> getDiskInfo(Map<String,String> param){
        Map<String,Object> map = new HashMap<>();
        try {
        String params = XmlGenerator.generateHaveNotParam(param);
        String xmlResponse = HttpUtils.httpPostByXml(getSocketAddress(param)+Constants.MedT100.getDiskInfo,params);
        System.out.println("getDiskInfoMessage:"+xmlResponse);
        Element root = null;
        Document document = DocumentHelper.parseText(xmlResponse);
        root = document.getRootElement();
        map.put("NakedPartSize",root.elementText("NakedPartSize"));
        map.put("NakedPartFreeSize",root.elementText("NakedPartFreeSize"));
        map.put("Mp4PartSize",root.elementText("Mp4PartSize"));
        map.put("Mp4PartFreeSize",root.elementText("Mp4PartFreeSize"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }
    /**
     *
     * @param xml
     * @return JSONObject
     * @description   xml转json
     */
    public  JSONObject xmlToJson(String xml){
        //api.xmlToJson(xmlResponse).get("Authenticationid").toString()
        XMLSerializer xmlSerializer=new XMLSerializer();
        return  (JSONObject)xmlSerializer.read(xml);
    }


    /**
     *
     * @param param
     * @return string
     * @description   获取地址
     */
    public String getSocketAddress(Map<String,String> param){
        return String.format("http://%s:%s/", param.get("ip"), param.get("port"));
    }

    public void initMedT100(final Map<String, String> login){
        final ExecutorService service = ThreadPoolUtils.getThreadPool();
        final MedTApi _this=this;
        service.execute(new Runnable() {
            @Override
            public void run() {
                boolean outFlag=true;
                while(outFlag){
                    String authenticationId = _this.getAuthenticationId(login);
                    _this.flag.put(login.get("id"), true);
                    if(null!=authenticationId){
                        login.put("authenticationid",authenticationId);
                        XmlGenerator.generateRequestRoot(login);
                        if(_this.Login(login)){
                            _this.SetSysTimeInfo(login);
                            AuthenticThread runnable = new AuthenticThread(login, _this);
                            MedRunnableMap.map.put(login.get("id"), runnable);
                            service.execute(runnable);
                            return;
                        }
                        System.out.println(login.get("hospitalName")+"login fail check your username or password");
                        logger.error(login.get("hospitalName")+"login fail check your username or password");
                    }
                    else{
                        logger.error(login.get("hospitalName")+":authentication fail");
                        System.out.println(login.get("hospitalName")+":authentication fail");
                    }
                    if (_this.flag.get(login.get("id"))!=null&&_this.flag.get(login.get("id"))) {
                        try {
                            Thread.sleep(5000);
                            logger.error(login.get("hospitalName")+":try connect again");
                            System.out.println(login.get("hospitalName")+":try connect again");
                            outFlag=false;
                            initMedT100(login);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public Map<String,Boolean> getFlag() {
        return flag;
    }
}
