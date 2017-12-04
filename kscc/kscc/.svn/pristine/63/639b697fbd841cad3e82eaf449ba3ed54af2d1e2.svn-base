package com.fable.kscc.bussiness.controller.aws;

import java.io.File;
import java.net.URLDecoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.fable.kscc.api.config.init.SysConfig;
import com.fable.kscc.api.model.page.PageRequest;
import com.fable.kscc.api.model.page.PageResponse;
import com.fable.kscc.api.model.page.ResultKit;
import com.fable.kscc.api.model.page.ServiceResponse;
import com.fable.kscc.api.model.upload.FbsLiveFile;
import com.fable.kscc.api.skyCloud.Common;
import com.fable.kscc.bussiness.service.uploadfile.UploadFileService;

@RequestMapping("/awsFile")
@Controller
public class AwsFileController {
    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 删除文件
     */
    @RequestMapping("/deleteFile")
    @ResponseBody
    public ServiceResponse deleteFile(@RequestBody Map<String,String> map) throws Exception {
    	try {
    		uploadFileService.deleteFile(map.get("bucket"), map.get("filename"),map.get("fileId"));
    		return ResultKit.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultKit.fail("删除失败");
		}
    }
    
    /**
     * 下载链接
     */
    @RequestMapping("/getFile")
    @ResponseBody
    public String getFile( @RequestBody Map<String,String> map ) throws Exception {
    	try {
    		//String url = "http://www.karlstorz.com.oos-website-cn.oos-hq-sh.ctyunapi.cn/"+map.get("bucket")+"/"+map.get("filename");
    		String bucketName = SysConfig.getValueByKey("skyCloud.bucketName");
    		AmazonS3Client client = Common.getClient();
    		GeneratePresignedUrlRequest httpRequest=new GeneratePresignedUrlRequest(bucketName, map.get("bucket")+"/"+map.get("filename"));
    		String url=client.generatePresignedUrl(httpRequest).toString();//临时链接
    		return url;
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败";
		}
    }
    
    /**
     * 修改文件名
     */
    @RequestMapping("/renameFile")
    @ResponseBody
    public ServiceResponse renameFile(@RequestBody Map<String,String> map  ) throws Exception {
    	try {
    		uploadFileService.renameFile(map.get("bucket"), map.get("filename"),  map.get("name"),map.get("fileId"));
    		return ResultKit.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultKit.fail("操作失败");
		}
    }
    
    
    /**
     * @param   title  
    	   hospital 
    	  uploadstatus 
    	    timeBegin timeEnd  
     *  pageRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/listFile", method=RequestMethod.POST)
	@ResponseBody
	public PageResponse<FbsLiveFile> listFile(@RequestBody PageRequest<Map<String,Object>> pageRequest) throws Exception {
    	//public PageResponse<FbsLiveFile> listFile() throws Exception {
    	try {
    		return uploadFileService.getLiveFileList(pageRequest);
		} catch (Exception e) {
			e.printStackTrace();
			return new PageResponse<FbsLiveFile>();
		}
	}

}
