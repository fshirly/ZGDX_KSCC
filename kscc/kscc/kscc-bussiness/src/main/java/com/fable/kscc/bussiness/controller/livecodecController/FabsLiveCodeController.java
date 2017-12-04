package com.fable.kscc.bussiness.controller.livecodecController;

import com.fable.kscc.api.model.liveCodec.FbsLiveCodec;
import com.fable.kscc.api.model.page.PageRequest;
import com.fable.kscc.api.model.page.PageResponse;
import com.fable.kscc.api.model.page.ServiceResponse;
import com.fable.kscc.bussiness.service.livecodec.LiveCodecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2017/9/11 0011.
 */
@RequestMapping("/fabsLiveCode")
@Controller
public class FabsLiveCodeController {
    @Autowired
    private LiveCodecService liveCodecService;

    @SuppressWarnings("unchecked")
    @RequestMapping("/findAllPageCodeList")
    @ResponseBody
    public PageResponse<FbsLiveCodec> findAllPageCodeList(@RequestBody PageRequest<FbsLiveCodec> request)  {
        return liveCodecService.findAllPageLiveCodeList(request);
    }

    @RequestMapping("/addLiveCoder")
    @ResponseBody
    public boolean addLiveCoder(@RequestBody Map<String,Object> params) {
        boolean insertFlag = liveCodecService.insertLiveCode(params);
        return insertFlag;
    }

    @RequestMapping("/toDelLiveCodeById")
    @ResponseBody
    public ServiceResponse toDelLiveCodeById(String id) throws Exception {
        return liveCodecService.deleteCode(id);
    }

    @RequestMapping("/getLiveCodeInfoById")
    @ResponseBody
    public ServiceResponse getLiveCodeInfoById(@RequestBody Map<String,Object> params){
        FbsLiveCodec code = new FbsLiveCodec();
        code.setId(Integer.parseInt(params.get("id").toString()));
        return liveCodecService.getCode(code);
    }

    @RequestMapping("/updateLiveCode")
    @ResponseBody
    public ServiceResponse updateLiveCode(@RequestBody Map<String,Object> params){
        return liveCodecService.updateLiveCode(params);
    }
}
