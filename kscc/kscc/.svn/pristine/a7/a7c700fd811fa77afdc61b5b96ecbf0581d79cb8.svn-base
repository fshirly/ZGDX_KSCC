package com.fable.kscc.bussiness.mapper.fbsDoctor;

import com.fable.kscc.api.model.doctor.FbsDoctor;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/27 0027.
 */
public interface FbsDoctorMapper {
    /**
     *查询单个医生用户信息
     */
    FbsDoctor queryFbDoctorUser(FbsDoctor user);
    /**
     * 查询医生用户列表
     * @param user
     * @return
     */
    List<FbsDoctor> findAllPageHopitalUserList(FbsDoctor user);
    /**
     * 新增医生用户
     */
    int insertFbDoctor(FbsDoctor doctor);

    /**
     *更新医生用户
     */
    int updateDoctorFbUser(@Param("params") Map<String,Object> params);

    /**
     *删除医生用户
     */
    int deleteFbsDoctor(int[] num);
}
