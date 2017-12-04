package com.fable.kscc.api.skyCloud;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.fable.kscc.api.config.init.SysConfig;

public class Common
{
    public static AmazonS3Client getClient()
    {
        AmazonS3Client client = new AmazonS3Client(new AWSCredentials()
        {
            public String getAWSAccessKeyId()
            {
                return SysConfig.getValueByKey("skyCloud.ownerName");
            }
            
            public String getAWSSecretKey()
            {
                return SysConfig.getValueByKey("skyCloud.secret");
            }
        });
        client.setEndpoint(SysConfig.getValueByKey("skyCloud.oosDomain"));
        return client;
    }
}
