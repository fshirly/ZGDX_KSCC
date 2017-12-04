package com.fable.kscc.bussiness.service.fbsdoctor;

import com.fable.kscc.api.exception.BussinessException;
import com.fable.kscc.api.model.doctor.FbsDoctor;
import com.fable.kscc.api.model.page.PageRequest;
import com.fable.kscc.api.model.page.PageResponse;
import com.fable.kscc.api.model.user.FbsUser;

/**
 * Created by Administrator on 2017/10/27 0027.
 */
public interface FbsDoctorService {
    /**
     * 查询单个用户
     */
    FbsDoctor getDoctorUser(FbsDoctor doctor);
    /**
     * 查询医生用户信息
     * @param page
     * @return
     */
    PageResponse<FbsDoctor> findAllPageHopitalUserList(PageRequest<FbsDoctor> page);
    /**
     * 删除医生用户
     */
    int deleteDoctorUser(String ids) throws BussinessException;
}
