package com.fable.kscc.bussiness.websocket;

/**
 * <p>
 * Title :
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Author :王海瑞 2016/11/17
 * </p>
 * <p>
 * Department :
 * </p>
 * <p> Copyright : 江苏飞博软件股份有限公司 </p>
 */

import com.fable.kscc.api.utils.ApplicationContextBeanUtil;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket/{confId}")
public class WebSocketService {

    private static int onlineCount = 0;
    private Session session;
    private static CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<Session>();
    private Sender sender = ApplicationContextBeanUtil.getBeanByType(Sender.class);
    @OnOpen
    public void onOpen(Session session) {
        String confId=session.getPathParameters().get("confId");
        if(sender.getSessionsOfConf().get(confId)==null){
            CopyOnWriteArraySet<Session> confSession=sender.getSessions();
            sender.getSessionsOfConf().put(confId, confSession);
        }
        sender.getSessionsOfConf().get(confId).add(session);
        sessions.add(session);
        this.session = session;
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    @OnClose
    public void onClose() {
        Map<String, CopyOnWriteArraySet<Session>> sessionsOfConf = sender.getSessionsOfConf();
        Iterator<String> key=sessionsOfConf.keySet().iterator();
        while (key.hasNext()){
            String confId=key.next();
            CopyOnWriteArraySet<Session> confSession=sessionsOfConf.get(confId);
            if(confSession.contains(session)){
                confSession.remove(session);
                if(confSession.size()==0){
                    key.remove();
                    break;
                }
            }
        }
        sessions.remove(session);
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }


    //收到客户端端消息时触发
    @OnMessage
    public void onMessage(String message, Session session) {
        for(Session link:sessions){
            try {
                link.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
  /*  private void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }*/

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketService.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        WebSocketService.onlineCount--;
    }

}