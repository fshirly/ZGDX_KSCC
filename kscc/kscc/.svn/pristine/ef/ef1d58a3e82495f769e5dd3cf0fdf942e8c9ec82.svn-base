package com.fable.kscc.api.skyCloud;

import java.io.IOException;
import java.io.InputStream;

import com.fable.kscc.api.skyCloud.Common;
import com.fable.kscc.api.utils.FileUtil;
import org.apache.commons.io.IOUtils;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
public class SimpleObjectOperate
{
    private static String bucketName = "ctyun-test";
    
    /**
     * 天翼云上传分两个类型 上传<br/>
     * 1.文件小，可以选择整体上传<br/>
     * 2.文件大，需要开始分片上传<br/>
     * 管理后台地址：http://oos.ctyun.cn/oos/v2/console.html
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        // 获取oss连接
        AmazonS3Client client = Common.getClient();
        
        // 获取文件列表
        listObject(client);
        
        // 新增简单对象
        try
        {
            simpleUploadObject(client, "ts-" + System.currentTimeMillis());
        }
        catch (IOException e)
        {
            System.out.println("简单文件上传失败：" + e.getMessage());
            e.printStackTrace();
        }
        // 获取文件列表
        listObject(client);
    }
    
    /**
     * 获取对象类别（指定存储桶）
     * 
     * @param client
     */
    public static void listObject(AmazonS3Client client)
    {
        System.out.println("------------listObject start---------------");
        ObjectListing list = client.listObjects(bucketName);
        for (S3ObjectSummary temp : list.getObjectSummaries())
        {
            System.out.println("fileName:" + temp.getKey() + " fileMD5:" + temp.getETag());
        }
        System.out.println("------------listObject end-----------------");
    }
    
    /**
     * 简单上传
     * 
     * @throws IOException
     */
    public static void simpleUploadObject(AmazonS3Client client, String objectName)
        throws IOException
    {
        PutObjectRequest putObjectRequest =
            new PutObjectRequest(bucketName, objectName, FileUtil.createSampleFile("123"));
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setCacheControl("no-cache");
        metadata.setContentDisposition("attachment; filename=testing.txt");
        metadata.setContentLength(3L);
        metadata.setContentType("text/plain");
        metadata.addUserMetadata("test", "metadata");
        putObjectRequest.setMetadata(metadata);
        // 开启上传
        client.putObject(putObjectRequest);
        
        // 获取文件属性
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, objectName);
        S3Object object = client.getObject(getObjectRequest);
        System.out.println("ContentLength:" + object.getObjectMetadata().getContentLength());
        System.out.println("ContentType():" + object.getObjectMetadata().getContentType());
        System.out.println("CacheControl:" + object.getObjectMetadata().getCacheControl());
        System.out.println("ContentDisposition:" + object.getObjectMetadata().getContentDisposition());
        InputStream input = null;
        try
        {
            input = object.getObjectContent();
            System.out.println("对象内容:" + IOUtils.toString(input));
        }
        finally
        {
            if (input != null)
                input.close();
        }
    }
}
