package com.fable.kscc.bussiness.service.medT100;

import com.fable.kscc.api.medTApi.MedTApi;
import com.fable.kscc.api.model.page.ResultKit;
import com.fable.kscc.api.model.page.ServiceResponse;
import com.fable.kscc.bussiness.mapper.uploadfile.UploadFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * Title :
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Author :Hairui
 * Date :2017/10/30
 * Time :16:12
 * </p>
 * <p>
 * Department :
 * </p>
 * <p> Copyright : 江苏飞博软件股份有限公司 </p>
 */
@Service
public class MedT100ServiceImpl implements IMedT100Service{

    @Autowired
    private MedTApi medTApi;

    @Autowired
    private UploadFileMapper mapper;

    @Override
    public ServiceResponse deleteMedT100File(Map<String, String> param) {
        //删除最后一个文件时，把目录删了
        int fileNum = mapper.getFileNum(param);
        Map<String, String>  response;
        if(fileNum==1){
            param.put("type", "0");
            response = medTApi.mp4FilesDelete(param);
            if("1".equals(response.get("success"))){
                mapper.updateFileMedTStatus(param);
                return ResultKit.success();
            }
            return ResultKit.fail(response.get("message"));
        }
        param.put("filePath", param.get("filePath") + param.get("fileName"));
         response=medTApi.mp4FilesDelete(param);
         if("1".equals(response.get("success"))){
             mapper.updateFileMedTStatus(param);
             return ResultKit.success();
         }
         return ResultKit.fail(response.get("message"));
    }
}
