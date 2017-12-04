package com.fable.kscc.bussiness.service.uploadfile.jobmanage;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.fable.kscc.api.utils.ApplicationContextBeanUtil;
import com.fable.kscc.bussiness.service.uploadfile.UploadFileService; 

public class UploadFileJob implements Job {

	private static UploadFileService uploadFileService;

	static {
		uploadFileService = (UploadFileService) ApplicationContextBeanUtil.getBean("uploadFileServiceImpl");
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("MP4  uploadjob start == " + new Date()); 
		try {
			uploadFileService.uploadFile(); 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("MP4  uploadjob  error ");
		} 
	}
}
