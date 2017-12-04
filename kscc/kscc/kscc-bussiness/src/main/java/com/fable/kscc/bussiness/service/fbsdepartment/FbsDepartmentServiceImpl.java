package com.fable.kscc.bussiness.service.fbsdepartment;

import com.fable.kscc.api.model.department.FbsDepartment;
import com.fable.kscc.bussiness.mapper.fbsdepartment.FbsDepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FbsDepartmentServiceImpl implements FbsDepartmentService{

    @Autowired
    private FbsDepartmentMapper fbsDepartmentMapper;

    //查询医院里的所有部门(科室)
    @Override
    public List<FbsDepartment> findAllDepartment() {
        return fbsDepartmentMapper.findAllDepartment();
    }
}
