package com.fable.kscc.bussiness.service.fbsdoctor;

import com.fable.kscc.api.exception.BussinessException;
import com.fable.kscc.api.model.doctor.FbsDoctor;
import com.fable.kscc.api.model.page.PageRequest;
import com.fable.kscc.api.model.page.PageResponse;
import com.fable.kscc.api.model.user.FbsUser;
import com.fable.kscc.bussiness.mapper.fbsDoctor.FbsDoctorMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/10/27 0027.
 */
@Service
public class FbsDoctorServiceImpl implements FbsDoctorService {
    @Autowired
    private FbsDoctorMapper fbsDoctorMapper;

    @Override
    public FbsDoctor getDoctorUser(FbsDoctor doctor) {
        return fbsDoctorMapper.queryFbDoctorUser(doctor);
    }

    @Override
    public PageResponse<FbsDoctor> findAllPageHopitalUserList(PageRequest<FbsDoctor> pageRequest) {
        FbsDoctor map=pageRequest.getParam();
        Page<FbsDoctor> result = PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());
        fbsDoctorMapper.findAllPageHopitalUserList(map);
        return PageResponse.wrap(result);
    }

    @Override
    public int deleteDoctorUser(String ids) throws BussinessException {
        String[] id = ids.split(",");
        int[] num = new int[id.length];
        for(int i=0; i<id.length;i++){
            num[i] = Integer.parseInt(id[i]) ;
        }
        return fbsDoctorMapper.deleteFbsDoctor(num);
    }
}
