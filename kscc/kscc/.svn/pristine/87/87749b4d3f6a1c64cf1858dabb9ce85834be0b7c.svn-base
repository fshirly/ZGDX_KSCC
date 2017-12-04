package com.fable.kscc.bussiness.service.uploadfile.jobmanage;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3Client;
import com.fable.kscc.api.skyCloud.Common;
import com.fable.kscc.api.skyCloud.MultObjectOperate;

@Component
public class MeetingUploadTask extends Thread implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(MeetingUploadTask.class);

	public MeetingUploadTask()
	{
		super();
	}
	
	private String bucket;
	
	private String path;

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void run() {
		logger.info( new Date() + "，上传任务开始 ，path =" + this.path + "，bucket = " + this.bucket);
		try {
			AmazonS3Client client = Common.getClient();
			MultObjectOperate mo = new MultObjectOperate(bucket);
			mo.multUploadObject(client, "ts-" + System.currentTimeMillis() + ".zip",path);
			File file = new File(path);
			if(file.exists())
			{
				//上传完成  删除原文件
				file.delete();
			}
		} catch (Exception e) {
			logger.error( new Date() + "，上传任务异常 ，path =" + this.path + "，bucket = " + this.bucket);
			e.printStackTrace();
		}
		logger.info( new Date() + "，上传任务结束 ，path =" + this.path + "，bucket = " + this.bucket);
	}
}
