package a592070.servlet;


import a592070.chat.HttpSessionConfigurator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.tomcat.websocket.WsSession;
import rambo0021.AccountBean;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/chat", configurator = HttpSessionConfigurator.class)
public class ChatWebSocket {
    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<ChatWebSocket> webSockets = new CopyOnWriteArraySet<>();
    private static ConcurrentHashMap<AccountBean, ChatWebSocket> webSocketMap = new ConcurrentHashMap<>();
    private static Map<ChatWebSocket, String> map = new HashMap<>();

    private Session session;
    private HttpSession httpSession;

    private String userChatId;
    private AccountBean user;
    private String ip;


    private Map<Long, String> historyMsg = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());

        String userChatId;
        if(httpSession.getAttribute("Login") != null){
            AccountBean user = (AccountBean) httpSession.getAttribute("Login");
            this.user = user;
        }else{
            this.user = new AccountBean();
            this.user.setUserName("guest"+session.getId());
        }

        System.out.println(getRemoteAddress((WsSession) session).getAddress().toString());
        this.ip = getRemoteAddress((WsSession) session).getAddress().toString();
        webSocketMap.put(this.user, this);

        addOnlineCount();
//        System.out.println("open "+onlineCount);
//        try {
//            JSONObject json = new JSONObject();
//            json.put("ip", ip);
//            session.getBasicRemote().sendText(json.toJSONString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println("ip:"+ip+"\thttpsession:"+httpSession.getId()+"\tsession: "+session.getId());
    }

    @OnClose
    public void onClose(){
//        webSockets.remove(this);
        webSocketMap.remove(this.user);
        subOnlineCount();
        System.out.println("close");
    }
    @OnMessage
    public void onMessage(String message, Session session) throws JsonProcessingException {
        System.out.println(message);

        historyMsg.put(System.currentTimeMillis(), message);
        session.getUserProperties().put("historyMsg", historyMsg);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> messageJson = new HashMap<>();
        messageJson = mapper.readValue(message, messageJson.getClass());

//        messageJson.put("ip", getRemoteAddress((WsSession) session).getAddress().toString());
        messageJson.put("user", this.user.getUserName());
        messageJson.put("count", String.valueOf(onlineCount));
        messageJson.put("currentTime", String.valueOf(System.currentTimeMillis()));

        try {
//            sendMessage(messageJson, session);
            System.out.println(messageJson);
            session.getBasicRemote().sendText(mapper.writeValueAsString(messageJson));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendMessage(Map<String, String> messageJson, Session session) throws IOException {
//        Set<Session> openSessions = session.getOpenSessions();
//        for (Session openSession : openSessions) {
//            openSession.getBasicRemote().sendText(messageJson.toJSONString());
//        }

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
