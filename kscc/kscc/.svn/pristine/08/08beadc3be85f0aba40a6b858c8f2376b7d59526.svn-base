package com.fable.kscc.bussiness.controller.fbsDoctor;

import com.fable.kscc.api.model.doctor.FbsDoctor;
import com.fable.kscc.api.model.page.PageRequest;
import com.fable.kscc.api.model.page.PageResponse;
import com.fable.kscc.api.model.user.FbsUser;
import com.fable.kscc.bussiness.service.fbsdoctor.FbsDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2017/10/27 0027.
 */
@RequestMapping("/fbsDoctor")
@Controller
public class FbsDoctorController {
    @Autowired
    private FbsDoctorService fbsDoctorService;

    @SuppressWarnings("unchecked")
    @RequestMapping("/findAllPageHospitalUserList")
    @ResponseBody
    public PageResponse<FbsDoctor> findAllPageHospitalUserList(@RequestBody PageRequest<FbsDoctor> request)  {
        return fbsDoctorService.findAllPageHopitalUserList(request);
    }

    @RequestMapping("/toDelFbDoctorById")
    @ResponseBody
    public boolean toDelFbDoctorById(String id) throws Exception {
        return fbsDoctorService.deleteDoctorUser(id) != 0;
    }

    @RequestMapping("/getDoctorInfoById")
    @ResponseBody
    public FbsDoctor getDoctorInfoById(@RequestBody Map<String,Object> params){
        FbsDoctor doctor = new FbsDoctor();
        doctor.setId(Integer.parseInt(params.get("id").toString()));
        return fbsDoctorService.getDoctorUser(doctor);
    }
}
