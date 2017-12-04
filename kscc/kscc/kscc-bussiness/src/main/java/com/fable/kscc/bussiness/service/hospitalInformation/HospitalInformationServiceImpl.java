package com.fable.kscc.bussiness.service.hospitalInformation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.amazonaws.services.s3.model.MultipartUploadListing;
import com.fable.kscc.api.exception.BussinessException;
import com.fable.kscc.api.medTApi.MedRunnableMap;
import com.fable.kscc.api.medTApi.MedTApi;
import com.fable.kscc.api.model.page.PageRequest;
import com.fable.kscc.api.model.page.PageResponse;
import com.fable.kscc.api.model.page.ResultKit;
import com.fable.kscc.api.model.page.ServiceResponse;
import com.fable.kscc.api.model.user.FbsUser;
import com.fable.kscc.api.model.userRole.FbsUserRole;
import com.fable.kscc.api.utils.MD5Encrypt;
import com.fable.kscc.api.utils.XmlGenerator;
import com.fable.kscc.bussiness.mapper.fbuserrole.FabUserRoleMapper;
import com.fable.kscc.bussiness.service.enrollMedT.MEDTInit;
import com.fable.kscc.bussiness.util.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.kscc.api.model.hospitalInformation.FbsHospitalInformation;
import com.fable.kscc.api.model.liveCodec.FbsLiveCodec;
import com.fable.kscc.bussiness.mapper.hospitalInformation.HospitalInformationMapper;
import com.fable.kscc.bussiness.mapper.fbsUser.FbsUserMapper;
import com.fable.kscc.bussiness.mapper.livecodec.LiveCodecMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HospitalInformationServiceImpl implements HospitalInformationService {

    @Autowired
    private HospitalInformationMapper hospitalInformationMapper;

    @Autowired
    private LiveCodecMapper liveCodecMapper;

    @Autowired
    private FbsUserMapper ksUserMapper;

    @Autowired
    private FabUserRoleMapper fabUserRoleMapper;

    @Autowired
    private MedTApi medTApi;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public List<FbsHospitalInformation> getHospitalInformationList(String id) {
        List<FbsHospitalInformation> hospitalBeanList = hospitalInformationMapper.getHospitalInformationList(id);
        //查询用户表中医院id为0的所有用户名称
        List<FbsUser> userList = ksUserMapper.queryUserByHospitalId();
        for (int i = 0; i < userList.size(); i++) {
            FbsHospitalInformation hosBean = new FbsHospitalInformation();
            String userName = userList.get(i).getUserName();
            hosBean.setHospitalName(userName);
            hospitalBeanList.add(hosBean);
        }
        return hospitalBeanList;
    }

    /**
     */
    @Override
    public List<Map<String, Object>> selectHospital(Map<String, Object> param) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        String searchCon = String.valueOf(param.get("searchCon"));
        params.put("searchCon", searchCon);
        List<FbsHospitalInformation> hospitalInfos = hospitalInformationMapper.selectHospital(params);
        for (FbsHospitalInformation hospitalInfo : hospitalInfos) {
            int id = hospitalInfo.getId();
            Map<String, Object> map = new HashMap<String, Object>();
            List<FbsLiveCodec> childMenuList = liveCodecMapper.findAllLiveCodec(id);
            map.put("id", hospitalInfo.getId().toString());
            map.put("name", hospitalInfo.getHospitalName());
            map.put("pid", "0");
            list.add(map);
            Map<String, Object> childmap = new HashMap<String, Object>();
            for (FbsLiveCodec fbsLiveCodec : childMenuList) {
                childmap.put("id", fbsLiveCodec.getId().toString() + hospitalInfo.getId().toString());
                childmap.put("name", fbsLiveCodec.getCodecOwnership());
                childmap.put("pid", fbsLiveCodec.getHospitalId().toString());
                list.add(childmap);
            }
        }
        return list;
    }

    /**
     */
    @Override
    public List<Map<String, Object>> selectHospitalInfo() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<FbsHospitalInformation> hospitalInfos = hospitalInformationMapper.selectHospitalInfo();
        for (FbsHospitalInformation hospitalInfo : hospitalInfos) {
            int id = hospitalInfo.getId();
            Map<String, Object> map = new HashMap<String, Object>();
            List<FbsLiveCodec> childMenuList = liveCodecMapper.findAllLiveCodec(id);
            map.put("id", hospitalInfo.getId().toString());
            map.put("name", hospitalInfo.getHospitalName());
            map.put("pid", "0");
            list.add(map);
            Map<String, Object> childmap = new HashMap<String, Object>();
            for (FbsLiveCodec fbsLiveCodec : childMenuList) {
                childmap.put("id", fbsLiveCodec.getId().toString() + hospitalInfo.getId().toString());
                childmap.put("name", fbsLiveCodec.getCodecOwnership());
                childmap.put("pid", fbsLiveCodec.getHospitalId().toString());
                list.add(childmap);
            }

        }
        return list;
    }

    /**
     * modifyCheckHospital
     */
    @Override
    public List<Map<String, Object>> checkHospital(String startTime, String endTime, String ids, String liveId) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("name", "");
        params.put("liveId", liveId);
        List<FbsHospitalInformation> hospitalInfos = hospitalInformationMapper.findAllHospitalInfo(params);//获取符合条件的医院
        List<String> idList = new ArrayList<>();
        for (FbsHospitalInformation info : hospitalInfos) {
            idList.add(info.getId() + "");
        }
        String[] id = ids.split(",");
        String str = "";
        Map<String, Object> map = new HashMap<String, Object>();
        int count = 0;

        checkUnion(hospitalInfos, idList, id, str, map, count);

        list.add(map);
        return list;
    }

    public List<Map<String, Object>> modifyCheckHospital(String startTime, String endTime, String oldStartTime, String oldEndTime, String ids, String liveId,String hospitalIds) {
        String[] id = ids.split(",");
        if(!hospitalIds.contains(id[0])){
            return checkHospital(startTime, endTime, ids, liveId);
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        Date start = null, end = null, oldStart = null, oldEnd = null;
        try {
            start = format.parse(startTime);
            end = format.parse(endTime);
            oldStart = format.parse(oldStartTime);
            oldEnd = format.parse(oldEndTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<FbsHospitalInformation> hospitalInfos;
        if (start.getTime() >= oldStart.getTime() && end.getTime() <= oldEnd.getTime()) {
            hospitalInfos = hospitalInformationMapper.getHospitalInformationList(id[0]);
        } else {
            if (start.getTime() >= oldStart.getTime() && start.getTime() < oldEnd.getTime()) {
                startTime = oldEndTime;
            } else if (end.getTime() <= oldEnd.getTime() && end.getTime() > oldStart.getTime()) {
                endTime = oldStartTime;
            }
            params.put("startTime", startTime);
            params.put("endTime", endTime);
            params.put("name", "");
            params.put("liveId", liveId);
            hospitalInfos = hospitalInformationMapper.findAllHospitalInfo(params);//获取符合条件的医院
        }
        List<String> idList = new ArrayList<>();
        for (FbsHospitalInformation info : hospitalInfos) {
            idList.add(info.getId() + "");
        }
        String str = "";
        Map<String, Object> map = new HashMap<String, Object>();
        int count = 0;

        checkUnion(hospitalInfos, idList, id, str, map, count);

        list.add(map);
        return list;
    }

    private void checkUnion(List<FbsHospitalInformation> hospitalInfos, List<String> idList, String[] id, String str, Map<String, Object> map, int count) {
        if (id.length == 1) {
            for (FbsHospitalInformation hospitalInfo : hospitalInfos) {
                String num = id[0];
                if (num.equals(hospitalInfo.getId().toString())) {
                    hospitalInfos.clear();
                    hospitalInfos.add(hospitalInfo);
                    map.put("list", hospitalInfos);
                    count = 1;
                    break;
                }
            }
            if (count != 1) {
                map.put("nonConformity", "当前医院");
            }
        } else {
            for (int i = 0; i < id.length; i++) {
                if (!idList.contains(id[i])) {
                    String nonHospitalName = hospitalInformationMapper.findAllById(Integer.parseInt(id[i]));
                    if ("".equals(str)) {
                        str = str + nonHospitalName;
                    } else {
                        str = str + "," + nonHospitalName;
                    }
                }
            }
            map.put("nonConformity", str);
            map.put("list", hospitalInfos);
        }
    }

    /**
     */
    @Override
    public FbsHospitalInformation getHospitalInfoByUser(int id) {
        FbsHospitalInformation hospitalInfo = new FbsHospitalInformation();
        int success = ksUserMapper.searchAdmin(id);
        if (success > 0) {
            return hospitalInfo;
        } else {
            hospitalInfo = hospitalInformationMapper.getHospitalInfoByUser(id);
//			hospitalInfo.setSerialNumber(1);
            return hospitalInfo;
        }
    }


    //日程校验(筛选出符合当前直播间时间的参与方医院)邀请参与方页面接口
    @Override
    public List<Map<String, Object>> screenHospital(Map<String, Object> params) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Integer> param = (List<Integer>) params.get("hospitalIds");
        List<FbsHospitalInformation> hospitalInfos = hospitalInformationMapper.getHospitalForAddParticipant(param);//获取符合条件的医院
        for (int i = 0; i < hospitalInfos.size(); i++) {
            int id = hospitalInfos.get(i).getId();
            Map<String, Object> map = new HashMap<String, Object>();
            List<FbsLiveCodec> childMenuList = liveCodecMapper.findAllLiveCodec(id);
            map.put("id", hospitalInfos.get(i).getId().toString());
            map.put("name", hospitalInfos.get(i).getHospitalName());
            map.put("newvoidNum", hospitalInfos.get(i).getNewVideoNum());
            map.put("pid", "0");
            list.add(map);
            Map<String, Object> childmap = new HashMap<String, Object>();
            for (int j = 0; j < childMenuList.size(); j++) {
                childmap.put("id", childMenuList.get(j).getId().toString() + hospitalInfos.get(j).getId().toString());
                childmap.put("name", childMenuList.get(j).getCodecOwnership());
                childmap.put("pid", childMenuList.get(j).getHospitalId().toString());
                childmap.put("newvoidNum", childMenuList.get(j).getNewvideoNum().toString());
                list.add(childmap);
            }
        }
        return list;
    }

    @Override
    public FbsHospitalInformation selectHospitalById(Map params) {

        List<FbsHospitalInformation> fbsHospitalInformations = hospitalInformationMapper.selectHospitalById(params);
        return fbsHospitalInformations.get(0);
    }

    @Override
    @Transactional
    public ServiceResponse insertHospitalInfo(Map params) {

        Map<String, String> map = new HashMap<>();
        map.put("ip", params.get("ip").toString());
        map.put("port", params.get("port").toString());
        map.put("username", params.get("username").toString());
        map.put("password", params.get("code_password").toString());
        map.put("hospitalName", params.get("hospitalName").toString());
        String response = medTApi.getAuthenticationId(map);
        if (response == null) {
            return ResultKit.fail("编解码异常，请检查ip地址或者端口号是否有误，或者编解码是否开启");
        }
        map.put("authenticationid",response);
        XmlGenerator.generateRequestRoot(map);
        if(!medTApi.Login(map)){
            return ResultKit.fail("编解码异常，编解码用户名或密码有误");
        }
        //医院
        FbsHospitalInformation information = new FbsHospitalInformation();
        information.setHospitalName(params.get("hospitalName").toString());
        information.setHospitalContent(params.get("hospitalContent").toString());
        information.setHospitalUrl(params.get("hospitalUrl").toString());
        //入值bucket
        information.setBucket(params.get("newvideoNum").toString());
        //入值医院地址
        //对医院入值地址做处理
        StringUtil util = new StringUtil();
        String location = util.locationValue(params.get("location").toString());
        information.setLocation(location);
        hospitalInformationMapper.insertFbsHospitalInfo(information);

        //用户
        FbsUser user = new FbsUser();
        String pass = MD5Encrypt.encode(params.get("user_password").toString());
        user.setPassword(pass);
        user.setLoginName(params.get("loginName").toString());
        //新增管理员userName名称
        user.setUserName(params.get("userName").toString());
        user.setMobilePhone(params.get("mobilePhone").toString());
        user.setEmail(params.get("email").toString());
        user.setHospitalId(information.getId());
        //入值hostLevel 普通医院用户入值为0
        user.setHostLevel("0");
        ksUserMapper.insertFbUser(user);
        FbsUserRole roleBean = new FbsUserRole();
        roleBean.setUserId(user.getId().toString());
        roleBean.setRoleId("2");//医院管理员角色
        fabUserRoleMapper.insertFabUserRole(roleBean);


        //编解码
        FbsLiveCodec code = new FbsLiveCodec();
        code.setCodecOwnership(params.get("codecOwnership").toString());
        code.setIp(params.get("ip").toString());
        //code.setMac(params.get("mac").toString());
        code.setNewvideoNum(params.get("newvideoNum").toString());
        //增加端口号，用户名，密码
        code.setPort(params.get("port").toString());
        code.setUserName(params.get("username").toString());
        code.setPassword(params.get("code_password").toString());
        //FTPport端口
        code.setFtpPort(params.get("ftpPort").toString());
        code.setHospitalId(information.getId());
        //编解码器归属科室、备注
        code.setDepartmentId(Integer.parseInt(params.get("departmentId").toString()));
        code.setRemarks(params.get("remarks").toString());
        //id,IP ip,PORT port,USERNAME username,PASSWORD password,HOSPITAL_ID hospitalId,HOSPITAL_NAME hospitalName
        liveCodecMapper.insertLiveCode(code);
        //编解码加入心跳线程
        map.put("id", code.getId().toString());
        map.put("hospitalId", information.getId().toString());
        medTApi.initMedT100(map);
        return ResultKit.success();

    }

    @Override
    public List<Map<String, Object>> queryTreeInfo(Map<String, Object> param) {
        //查询不同用户所能看到的医院信息
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<FbsHospitalInformation> hospitalInfos = new ArrayList<FbsHospitalInformation>();
        int userId = Integer.parseInt(param.get("id").toString());
        //根据用户id查询相应的角色
        String roleId = fabUserRoleMapper.queryRoleByUserId(userId).getRoleId();
        if (1 == Integer.parseInt(roleId)) { //kscc管理员用户
            Map<String, Object> map0 = new HashMap<String, Object>();
            map0.put("id", 0);
            map0.put("pid", 0);
            map0.put("name", "医院列表");
            map0.put("address", "/loginController/toHospitalList");
            list.add(map0);

            hospitalInfos = hospitalInformationMapper.selectHospitalInfo();
            for (int i = 0; i < hospitalInfos.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                int id = hospitalInfos.get(i).getId();
                //通过医院id查询相应的管理员用户id
                Map<String, Object> paraMap = new HashMap<>();
                paraMap.put("hospitalId", id);
                FbsUser userBean = ksUserMapper.queryHostUser(paraMap);

                map.put("id", id);
                map.put("pid", hospitalInfos.get(i).getId().toString());
                map.put("name", hospitalInfos.get(i).getHospitalName().toString());
                map.put("address", "/loginController/toHospitalInfo?id=" + id);
                list.add(map);
                Map<String, Object> map1 = new HashMap<String, Object>();
                map1.put("id", id + 1);
                map1.put("pid", id);
                map1.put("name", "管理员");
                if (null != userBean) {
                    map1.put("address", "/loginController/toHospitalAdmin?id=" + id);
                } else {
                    map1.put("address", "/loginController/toHospitalAdmin?id=" + id);
                }
                list.add(map1);
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("id", id + 2);
                map2.put("pid", id);
                map2.put("name", "医生用户");
                map2.put("address", "/loginController/toDoctorUser?id=" + id);
                list.add(map2);
                Map<String, Object> map3 = new HashMap<String, Object>();
                map3.put("id", id + 3);
                map3.put("pid", id);
                map3.put("name", "编解码器");
                map3.put("address", "/loginController/toCodec?id=" + id);
                list.add(map3);
            }
        } else {
            //普通医院管理员用户
            FbsHospitalInformation hospital = hospitalInformationMapper.getHospitalInfoByUser(userId);
            int hospitalId = hospital.getId();
            //通过医院id查询相应的管理员用户id
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("hospitalId", hospitalId);
            Map<String, Object> map = new HashMap<String, Object>();
            int id = hospital.getId();
            map.put("id", id);
            map.put("pid", hospital.getId().toString());
            map.put("name", hospital.getHospitalName().toString());
            map.put("address", "/loginController/toHospitalInfo?id=" + hospitalId);
            list.add(map);
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("id", id + 1);
            map1.put("pid", id);
            map1.put("name", "管理员");
            map1.put("address", "/loginController/toHospitalAdmin?id=" + hospitalId);
            list.add(map1);
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("id", id + 2);
            map2.put("pid", id);
            map2.put("name", "医生用户");
            map2.put("address", "/loginController/toDoctorUser?id=" + id);
            list.add(map2);
            Map<String, Object> map3 = new HashMap<String, Object>();
            map3.put("id", id + 3);
            map3.put("pid", id);
            map3.put("name", "编解码器");
            map3.put("address", "/loginController/toCodec?id=" + id);
            list.add(map3);
        }
        return list;
    }

    @Override
    public PageResponse<FbsHospitalInformation> findAllPageLiveHospitalList(PageRequest<FbsHospitalInformation> pageRequest) {
        FbsHospitalInformation map = pageRequest.getParam();
        Page<FbsHospitalInformation> result = PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());
        hospitalInformationMapper.findAllPageLiveHospitalList(map);
        return PageResponse.wrap(result);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ServiceResponse updateLiveHospital(Map<String, Object> params) {
        //医院地址处理
        StringUtil util = new StringUtil();
        String realLocation = util.locationValue(params.get("location").toString());
        params.put("location",realLocation);
        hospitalInformationMapper.updateLiveHospital(params);
        return ResultKit.success();
    }

    @Override
    public FbsHospitalInformation getHospital(FbsHospitalInformation hospital) {
        return hospitalInformationMapper.queryLiveHospital(hospital);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ServiceResponse deleteHospital(String ids) throws BussinessException {
        //批量删除，变为单个删除
        String[] id = ids.split(",");
        int hospitalId = Integer.parseInt(id[0]);
        //删除相应的管理员用户和编解码器信息
        FbsUser userBean = ksUserMapper.getFbUserByhospitalId(hospitalId);
        List<Map<String,String>> FbsLiveCodecs = liveCodecMapper.getCodecByHospitalId(hospitalId);
        fabUserRoleMapper.deleteUserRoleById(userBean.getId());
        ksUserMapper.deleteFbUserByhospitalId(hospitalId);
        liveCodecMapper.deleteCodeByHospitalId(hospitalId);
        int deleteFlag = hospitalInformationMapper.deleteLiveHospital(hospitalId);
        if (deleteFlag == 1) {
            //1.编解码正常但被删除了，终结编解码线程,
            // 2.编解码异常(死机，关机，重启中)被删除，终结编解码递归重连循环
            for(Map<String,String> map:FbsLiveCodecs){
                if(medTApi.getAuthenticationId(map)!=null){//正常
                    MedRunnableMap.map.get(map.get("id")).setFlag(false);
                }
                else{//异常
                    medTApi.getFlag().remove(map.get("id"));
                }
            }
            return ResultKit.success();
        }
        return ResultKit.fail();
    }

}

