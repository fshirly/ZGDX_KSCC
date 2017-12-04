package com.fable.kscc.bussiness.controller.medT100;

import com.fable.kscc.api.model.page.ServiceResponse;
import com.fable.kscc.bussiness.service.medT100.IMedT100Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * Time :16:07
 * </p>
 * <p>
 * Department :
 * </p>
 * <p> Copyright : 江苏飞博软件股份有限公司 </p>
 */
@Controller
@RequestMapping("/medT100")
public class MedT100Controller {

    @Autowired
    IMedT100Service service;

    @ResponseBody
    @RequestMapping("/deleteMedT100File")
    public ServiceResponse deleteMedT100File(@RequestBody Map<String,String> param){
        return service.deleteMedT100File(param);
    }
}
