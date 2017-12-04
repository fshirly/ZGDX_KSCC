package com.fable.kscc.bussiness.mapper.livecodec;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.s3.model.MultipartUploadListing;
import org.apache.ibatis.annotations.Param;

import com.fable.kscc.api.model.liveCodec.FbsLiveCodec;

public interface LiveCodecMapper {

	/**
	 * 根据id查看编解码器(手术室)
	 * @param id
	 * @return
	 */
	List<FbsLiveCodec> findAllLiveCodec(int id);
	
	/**
	 * 根据参与方医院id查询新世通账号
	 * @param id
	 * @return
	 */
	String findNewVideoNum(@Param("id") int id);

	/**
	 * 根据新世通账号查询医院id
	 * @param newVideoNum
	 * @return
	 */
	List<Integer> findIdByNewVideoNum(@Param("newVideoNum") String newVideoNum);

	/**
	 * 查询编解码器分页
	 * @param codec
	 * @return
	 */
	List<FbsLiveCodec> findAllPageLiveCodeList(FbsLiveCodec codec);

	/**
	 * 新增编码器设备
	 * @param codec
	 * @return
	 */
	int insertLiveCode(FbsLiveCodec codec);

	/**
	 * 删除编解码
	 * @param num
	 * @return
	 */
	int deleteLiveCode(int[] num);

	/**
	 * 查询单个编解码器信息
	 * @param codec
	 * @return
	 */
	FbsLiveCodec queryLiveCode(FbsLiveCodec codec);

	/**
	 * 编辑编解码器信息
	 * @param params
	 * @return
	 */
	int updateLiveCode(@Param("params") Map<String,Object> params);

	List<Map<String, String>> selectMedTProperties();

	int deleteCodeByHospitalId(Integer hospitalId);

	List<Map<String,String>> getCodecByHospitalId(@Param("hospitalId") int id);

	List<FbsLiveCodec> findAllLiveCodecForYw(Map<String, String> params);

	int updateLiveCodeTitle(@Param("params") Map<String,String> params);

	FbsLiveCodec queryLiveCodeTitle(@Param("params") Map<String,Object> params);
}
