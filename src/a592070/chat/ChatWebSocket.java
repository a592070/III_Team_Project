package a592070.chat;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import globalinit.Constant;
import org.apache.tomcat.websocket.WsSession;
import rambo0021.AccountBean;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/chat", configurator = HttpSessionConfigurator.class)
public class ChatWebSocket {
    private static int onlineCount = 0;
//    private static CopyOnWriteArraySet<ChatWebSocket> webSockets = new CopyOnWriteArraySet<>();

    //(httpSession Id , WebSocket)
    private static ConcurrentHashMap<String, ChatWebSocket> webSocketClient = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, ChatWebSocket> webSocketService = new ConcurrentHashMap<>();

    private Session session;
    private HttpSession httpSession;

    private String username;
    private AccountBean user;
    private String ip;


    private Map<Long, String> historyMsg;

    private ObjectMapper mapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        this.session = session;
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());

        // application attribute (httpSession Id ,username)
//        Map<String, String> applicationClients;
//        if(httpSession.getServletContext().getAttribute(Constant.CHATROOM_CLIENTS) != null){
//            applicationClients = (Map<String, String>) httpSession.getServletContext().getAttribute(Constant.CHATROOM_CLIENTS);
//        }else{
//            applicationClients = new HashMap<>();
//        }

        String userChatId;
        if(httpSession.getAttribute(Constant.LOGIN) != null){
            AccountBean user = (AccountBean) httpSession.getAttribute(Constant.LOGIN);
            this.user = user;
            if(user.getIdentity() == 1){
                webSocketService.put(httpSession.getId(), this);
            }else{
                webSocketClient.put(httpSession.getId(), this);
//                applicationClients.put(httpSession.getId(), user.getUserName());
                addClient(httpSession.getId());
            }
        }else{
            webSocketClient.put(httpSession.getId(), this);
//            applicationClients.put(httpSession.getId(), "Guest"+httpSession.getId());
            addClient(httpSession.getId());
        }
        this.user = new AccountBean();


        System.out.println(getRemoteAddress((WsSession) session).getAddress().toString());
        this.ip = getRemoteAddress((WsSession) session).getAddress().toString();


        addOnlineCount();

        System.out.println("ip:"+ip+"\thttpsession:"+httpSession.getId()+"\tsession: "+session.getId());

//        sendMessage(historyMsg, session);
    }

    @OnClose
    public void onClose() throws IOException {
        if(webSocketClient.contains(httpSession)){
            webSocketClient.remove(httpSession);
            removeClient(httpSession.getId());
        }
        if(webSocketService.contains(httpSession)){
            webSocketService.remove(httpSession);
        }

        subOnlineCount();
        System.out.println("close");
    }
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println(message);

//        historyMsg.put(System.currentTimeMillis(), message);


        Map<String, String> messageJson = mapper.readValue(message, new TypeReference<Map<String, String>>(){});

        String send = messageJson.get("send");
        String receive = messageJson.get("receive");
        String content = messageJson.get("content");

        if(this.user.getIdentity() == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("method","toClient");
            objectNode.put("content", content);
            webSocketClient.forEach((k,v)->{
                if(k.equals(receive)){
                    try {
                        sendMessageToClient(objectNode, v.session);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }else{
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("method","toService");
            objectNode.put("httpSessionID", httpSession.getId());
            objectNode.put("content", content);
            sendMessageToService(objectNode);
        }
    }

    public synchronized void sendMessageToService(ObjectNode objJson) throws IOException {

        String str = mapper.writeValueAsString(objJson);
        webSocketService.forEach((k,v) ->{
            try {
                v.session.getBasicRemote().sendText(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public synchronized void sendMessageToClient(ObjectNode objJson, Session session) throws IOException {

        String str = mapper.writeValueAsString(objJson);
        session.getBasicRemote().sendText(str);
    }

    public synchronized void addClient(String httpSessionID) throws IOException {
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("method", "addClient");
        objectNode.put("httpSessionID", httpSessionID);

        sendMessageToService(objectNode);
    }
    public synchronized void removeClient(String httpSessionID) throws IOException {
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("method", "removeClient");
        objectNode.put("httpSessionID", httpSessionID);

        sendMessageToService(objectNode);
    }


    public void addOnlineCount(){
        ChatWebSocket.onlineCount++;
    }
    public void subOnlineCount(){
        ChatWebSocket.onlineCount--;
    }

    public static InetSocketAddress getRemoteAddress(WsSession session) {
        if(session == null){
            return null;
        }

        RemoteEndpoint.Async async = session.getAsyncRemote();
        InetSocketAddress addr = (InetSocketAddress) getFieldInstance(async,
                "base#socketWrapper#socket#sc#remoteAddress");
        return addr;
    }

    private static Object getFieldInstance(Object obj, String fieldPath) {
        String fields[] = fieldPath.split("#");
        for(String field : fields) {
            obj = getField(obj, obj.getClass(), field);
            if(obj == null) {
                return null;
            }
        }
        return obj;
    }

    private static Object getField(Object obj, Class<?> clazz, String fieldName) {
        for(;clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field field;
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(obj);
            } catch (Exception e) {
            }
        }
        return null;
    }
}
