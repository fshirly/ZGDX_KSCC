package com.fable.kscc.bussiness.controller.fbsdepartment;

import com.fable.kscc.api.model.department.FbsDepartment;
import com.fable.kscc.bussiness.service.fbsdepartment.FbsDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/FbsDepartment")
@Controller
public class FbsDepartmentController {

    @Autowired
    private FbsDepartmentService fbsDepartmentService;

    //查询医院里的所有部门(科室)
    @RequestMapping("/findAllDepartment")
    @ResponseBody
    List<FbsDepartment> findAllDepartment(){
       return fbsDepartmentService.findAllDepartment();
    }

}
