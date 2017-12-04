package com.fable.kscc.api.medTApi;

import com.fable.kscc.api.utils.ThreadPoolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther jiangmingjun
 * @create 2017/10/24
 * @description 单个鉴权线程类
 */
public class AuthenticThread implements Runnable {

    private boolean flag=true;
    public Map<String, String> login;
    public MedTApi medTApi;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticThread.class);

    public AuthenticThread(Map<String, String> login, MedTApi medTApi) {
        this.login = login;
        this.medTApi = medTApi;
    }

    @Override
    public void run() {
        while (flag){
            try {
                logger.info(login.get("ip")+":"+login.get("port")+"========================");
                Thread.sleep(10000);
                medTApi.heartBeat(login);//未catch捕捉到异常，会终止线程。
            } catch (InterruptedException e) {
                logger.error("线程出错"+e);
            }
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
