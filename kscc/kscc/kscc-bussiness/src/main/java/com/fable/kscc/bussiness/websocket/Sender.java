package com.fable.kscc.bussiness.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <p>
 * Title :
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Author :王海瑞 2017/8/22
 * </p>
 * <p>
 * Department :
 * </p>
 * <p> Copyright : 江苏飞博软件股份有限公司有限公司 </p>
 */
@Component
public class Sender {

    public static Logger logger = LoggerFactory.getLogger(Sender.class);

    private Map<String, CopyOnWriteArraySet<Session>> sessionsOfConf = new HashMap<>();

    public Map<String, CopyOnWriteArraySet<Session>> getSessionsOfConf() {
        return sessionsOfConf;
    }

    public CopyOnWriteArraySet<Session> getSessions() {
        return new CopyOnWriteArraySet<>();
    }

    public void sendData(String message,String confId){
        CopyOnWriteArraySet<Session> sessions=sessionsOfConf.get(confId);
        for (Session session:sessions){
            try {
                if(session!=null)
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                logger.error("推送数据到前台",e);
            }
        }
    }
}
