package com.fable.kscc.bussiness.service.medToServer;

import com.fable.kscc.api.medTApi.MedTApi;
import com.fable.kscc.api.skyCloud.DownFTPParam;
import com.fable.kscc.api.skyCloud.FtpUtil;
import com.fable.kscc.api.utils.Constants;
import com.fable.kscc.api.cache.DataCache;
import com.fable.kscc.bussiness.mapper.livebroadcast.LiveBroadCastMapper;
import com.fable.kscc.bussiness.mapper.uploadfile.UploadFileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * Title :
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Author :Administrator
 * Date :2017/10/19
 * Time :18:46
 * </p>
 * <p>
 * Department :
 * </p>
 * <p> Copyright : 江苏飞博软件股份有限公司 </p>
 */
@Service
public class MedFileToServer {

    @Autowired
    private MedTApi medTApi;

    @Autowired
    private LiveBroadCastMapper mapper;

    @Autowired
    private UploadFileMapper uploadFileMapper;

    private Logger logger = LoggerFactory.getLogger(MedFileToServer.class);

    private ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

   @PostConstruct
    public void init() {
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        Thread.sleep(60000);
                        List<Map<String, String>> list = mapper.getLiveOfEndButNotGenerate();
                        for (Map<String, String> param : list) {
                            DownFtpFile(param);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Transactional
    private void DownFtpFile(Map<String, String> param) {
        //跟新落地状态为进行中
        param.put("isGenerate", "3");
        mapper.modifyParticipantGenerate(param);
        String response = medTApi.Mp4BackupStart(param);
        if ("success".equals(response)) {
            fileProgress(param);
            if ("success".equals(param.get("isFileFinish"))) {
                String folder = medTApi.Mp4FileQuery(param);
                DownFTPParam downFTPParam = new DownFTPParam();
                downFTPParam.setPathSrc(folder);
                downFTPParam.setHost(param.get("ip"));
                downFTPParam.setUsername(param.get("username"));
                downFTPParam.setPassword(param.get("password"));
                downFTPParam.setHospitalName(param.get("hospitalName"));
                downFTPParam.setPort(Integer.parseInt(param.get("ftpPort")));
                String pathDst = System.getProperty("user.home")+ File.separator+"Fablesoft"+File.separator+"InsightView"+
                        File.separator+"third"+File.separator+"record"+File.separator + param.get("confId")+
                        File.separator+param.get("hospitalId")+File.separator;
                downFTPParam.setPathDst(pathDst);
                if (folder != null) {
                    Map<String, Object> responseForFtp = FtpUtil.FileDownload(downFTPParam);
                    if ("1".equals(responseForFtp.get("success"))) {
                        //跟新落地状态为已经落地
                        param.put("isGenerate", "1");
                        mapper.modifyParticipantGenerate(param);
                        List<String> fileNames = (List<String>) responseForFtp.get("fileNames");
                        param.put("filePath", folder);
                        for (String fileName : fileNames) {
                            param.put("fileName", fileName);
                            uploadFileMapper.insertIntoFile(param);
                        }
                    } else {
                        String message = String.format("hospital：%s，department：%s，meeting：%s,Ftp file to KSCC server happen Exception:%s",
                                param.get("hospitalName"), param.get("codecOwner"), param.get("title"),responseForFtp.get("reason"));
                        logger.error(message);
                        System.out.println(message);
                        param.put("isGenerate", null);
                        mapper.modifyParticipantGenerate(param);
                    }
                } else {
                    String message = String.format("hospital：%s，department：%s，meting：%s,query MP4 directory happen Exception can not find it",
                            param.get("hospitalName"), param.get("codecOwner"), param.get("title"));
                    logger.error(message);
                    System.out.println(message);
                    param.put("isGenerate",null);
                    mapper.modifyParticipantGenerate(param);
                }
            } else {
                String message = String.format("hospital：%s，department：%s，meting：%s,backup MP4 record progress fail",
                        param.get("hospitalName"), param.get("codecOwner"), param.get("title"));
                logger.error(message);
                System.out.println(message);
                param.put("isGenerate",null);
                mapper.modifyParticipantGenerate(param);
            }
        } else {
            //备份这一步出错了，停留在需要开始备份
            String message = String.format("hospital：%s，department：%s，meting：%s,backup MP4 record happen Exception",
                    param.get("hospitalName"), param.get("codecOwner"), param.get("title"));
            logger.error(message);
            System.out.println(message);
            param.put("isGenerate",null);
            mapper.modifyParticipantGenerate(param);
        }
    }

    private void fileProgress(final Map<String, String> param) {

        boolean flag = true;
        while (flag) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String response = medTApi.Mp4BackUpProgress(param);
            if ("0".equals(response)) {
                //没有文件在备份
                flag = false;
                param.put("isFileFinish", "errorRequest");
            } else if ("1".equals(response)) {
                //备份正在进行中
                flag = true;
            } else if ("2".equals(response)) {
                //备份完成
                flag = false;
                param.put("isFileFinish", "success");
            }
            else{
                flag=false;
                param.put("isFileFinish", "failure");
            }
        }

    }
}
