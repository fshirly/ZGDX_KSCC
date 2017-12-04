package com.fable.kscc.api.skyCloud;

import java.util.List;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.DeleteBucketRequest;
import com.amazonaws.services.s3.model.GetBucketAclRequest;
import com.amazonaws.services.s3.model.Grant;
import com.amazonaws.services.s3.model.Permission;

/**
 * 存储桶 操作<br/>
 * 这个我们很少使用,存储桶我们可以系统初始化
 * 一个医院一个存储桶，这个需要再表里加一个字段，二期处理
 * @author zhangy
 *
 */
public class BucketUserOpt
{
    
    private static final String bucketName;

    static{
        bucketName=ConfFactory.getBucketName();
    }
    
    public static void main(String[] args)
    {
        // 获取oss连接
        AmazonS3Client client = Common.getClient();
        listBucket(client);
        boolean isExist = checkBucket(client);
        System.out.println(bucketName + "(是否存在[true/false]):" + isExist);
        if (!isExist)
        {
            addBucket(client);
        }
    }
    
    /**
     * 获取存储桶列表
     * 
     * @param client
     */
    public static void listBucket(AmazonS3Client client)
    {
        List<Bucket> list = client.listBuckets();
        System.out.println("当前存在" + list.size() + "个存储桶，分别为：");
        for (Bucket temp : list)
        {
            System.out.println(temp.getName());
        }
        System.out.println("-------- list end");
    }
    
    /**
     * 判断bucket是否存在
     * 
     * @param client
     */
    public static boolean checkBucket(AmazonS3Client client)
    {
        return client.doesBucketExist(bucketName);
    }
    
    /**
     * 删除bucket
     * 
     * @param client
     */
    public static void delBucket(AmazonS3Client client)
    {
        DeleteBucketRequest deleteBucketRequest = new DeleteBucketRequest(bucketName);
        client.deleteBucket(deleteBucketRequest);
        System.out.println("删除" + bucketName + "成功!");
    }
    
    /**
     * 新增bucket
     * 
     * @param client
     */
    public static void addBucket(AmazonS3Client client)
    {
        client.createBucket(bucketName);
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
        createBucketRequest.setCannedAcl(CannedAccessControlList.PublicReadWrite);
        client.createBucket(createBucketRequest);
        GetBucketAclRequest getBucketAclRequest = new GetBucketAclRequest(bucketName);
        AccessControlList acl = client.getBucketAcl(getBucketAclRequest);
        for (Grant grant : acl.getGrants())
        {
            // assertEquals(grant.getPermission(), Permission.Read);
            System.out.println(grant.getPermission() + "----" + Permission.Read);
        }
        System.out.println("创建" + bucketName + "成功!");
    }
    
}
