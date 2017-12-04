package com.fable.kscc.bussiness.service.livecodec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fable.kscc.api.exception.BussinessException;
import com.fable.kscc.api.medTApi.MedRunnableMap;
import com.fable.kscc.api.medTApi.MedTApi;
import com.fable.kscc.api.model.hospitalInformation.FbsHospitalInformation;
import com.fable.kscc.api.model.page.PageRequest;
import com.fable.kscc.api.model.page.PageResponse;
import com.fable.kscc.api.model.page.ResultKit;
import com.fable.kscc.api.model.page.ServiceResponse;
import com.fable.kscc.api.utils.XmlGenerator;
import com.fable.kscc.bussiness.mapper.hospitalInformation.HospitalInformationMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fable.kscc.api.model.liveCodec.FbsLiveCodec;
import com.fable.kscc.bussiness.mapper.livecodec.LiveCodecMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LiveCodecServiceImpl implements LiveCodecService {

	@Autowired
	private LiveCodecMapper liveCodecMapper;

	@Autowired
	private HospitalInformationMapper hospitalInformationMapper;

	@Autowired
	MedTApi medTApi;
	/**
	 * 根据id查看编解码器(手术室)
	 */
	@Override
	public List<FbsLiveCodec> findAllLiveCodec(int id) {
		return liveCodecMapper.findAllLiveCodec(id);
	}

	/**
	 * 根据参与方医院id查询新世通账号
	 * @param id
	 * @return
	 */
	@Override
	public String findNewVideoNum(int id) {
		return liveCodecMapper.findNewVideoNum(id);
	}

	@Override
	public PageResponse<FbsLiveCodec> findAllPageLiveCodeList(PageRequest<FbsLiveCodec> pageRequest) {
		FbsLiveCodec map=pageRequest.getParam();
		Page<FbsLiveCodec> result = PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());
		liveCodecMapper.findAllPageLiveCodeList(map);
		return PageResponse.wrap(result);
	}

	@Override
	public boolean insertLiveCode(Map<String, Object> params) {
		FbsLiveCodec code = new FbsLiveCodec();
		code.setCodecOwnership(params.get("codecOwnership").toString());
		code.setIp(params.get("ip").toString());
		//code.setMac(params.get("mac").toString());
		code.setNewvideoNum(params.get("newvideoNum").toString());
		//增加端口号，用户名，密码
		code.setPort(params.get("port").toString());
		code.setUserName(params.get("username").toString());
		if("".equals(params.get("code_password"))){
			code.setPassword(params.get("password").toString());
		}else{
			code.setPassword(params.get("code_password").toString());
		}
		if("".equals(params.get("hospitalId"))){//.equal()){
			//通过医院名称查询相应的医院id
			FbsHospitalInformation bean = hospitalInformationMapper.getHospitalIdByName(params.get("hospitalName").toString());
			code.setHospitalId(bean.getId());
		}else{
			code.setHospitalId(Integer.parseInt(params.get("hospitalId").toString()));
		}
		int num = liveCodecMapper.insertLiveCode(code);
		if(num>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public ServiceResponse deleteCode(String ids) throws BussinessException {
		String[] id = ids.split(",");
		int[] num = new int[id.length];
		for(int i=0; i<id.length;i++){
			num[i] = Integer.parseInt(id[i]) ;
		}
		liveCodecMapper.deleteLiveCode(num);
		return ResultKit.success();
	}

	@Override
	public ServiceResponse getCode(FbsLiveCodec code) {
		FbsLiveCodec codeBean = null;
		try {
			codeBean = liveCodecMapper.queryLiveCode(code);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultKit.fail("查看编解码信息失败");
		}
		return  ResultKit.serviceResponse(codeBean);
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public ServiceResponse updateLiveCode(Map<String, Object> params) {
		Map<String, String> param = new HashMap<>();
		param.put("id", params.get("id").toString());
		param.put("ip", params.get("ip").toString());
		param.put("port", params.get("port").toString());
		param.put("username", params.get("username").toString());
		param.put("password", params.get("password").toString());
		String response = medTApi.getAuthenticationId(param);
		if (response == null) {
			return ResultKit.fail("编解码异常，请检查ip地址或者端口号是否有误，或者编解码是否开启");
		}
		param.put("authenticationid",response);
		XmlGenerator.generateRequestRoot(param);
		if(!medTApi.Login(param)){
			return ResultKit.fail("编解码异常，编解码用户名或密码有误");
		}
		int updateFlag=liveCodecMapper.updateLiveCode(params);
		if(updateFlag==1){
			//停止之前线程
			MedRunnableMap.map.get(params.get("id").toString()).setFlag(false);
			//重启新的线程
			medTApi.initMedT100(param);
			return ResultKit.success();
		}
		return ResultKit.fail("修改编解码表失败");
	}
}
