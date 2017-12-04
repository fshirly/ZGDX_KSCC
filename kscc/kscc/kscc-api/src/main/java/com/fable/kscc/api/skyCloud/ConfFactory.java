package com.fable.kscc.api.skyCloud;

import com.fable.kscc.api.config.init.SysConfig;

/**
 * Created by Wanghairui on 2017/8/30.
 */
public class ConfFactory {

    //编解码器配置
    private static final String host ;
    private static final String port;
    private static final String username;
    private static final String password;
    private static final String separator;
    private static final String saveDir;

    //天翼云配置
    private static final String ownerName;
    private static final String secret;
    private static final String oosDomain;
    private static final String bucketName;
    static{
        host = SysConfig.getValueByKey("ftp.host");
        port = SysConfig.getValueByKey("ftp.port");
        username = SysConfig.getValueByKey("ftp.username");
        password = SysConfig.getValueByKey("ftp.password");
        separator = SysConfig.getValueByKey("ftp.separator");
        saveDir = SysConfig.getValueByKey("ftp.saveDir");

        ownerName = SysConfig.getValueByKey("skyCloud.ownerName");
        secret = SysConfig.getValueByKey("skyCloud.secret");
        oosDomain = SysConfig.getValueByKey("skyCloud.oosDomain");
        bucketName = SysConfig.getValueByKey("skyCloud.bucketName");
    }

    public static String getHost() {
        return host;
    }

    public static String getPort() {
        return port;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getSeparator() {
        return separator;
    }

    public static String getSaveDir() {
        return saveDir;
    }

    public static String getOwnerName() {
        return ownerName;
    }

    public static String getSecret() {
        return secret;
    }

    public static String getOosDomain() {
        return oosDomain;
    }

    public static String getBucketName() {
        return bucketName;
    }
}
