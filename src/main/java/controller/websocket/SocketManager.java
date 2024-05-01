package controller.websocket;

import jakarta.websocket.Session;

import java.util.HashMap;

public class SocketManager {

    private static HashMap<String, Session> users = new HashMap<>();

    private static Session admin;

    public static HashMap<String, Session> getUserSessions() {
        return users;
    }

    public static  void  setUserSession(String id,Session session){
        users.put(id,session);
    }

    public static  void removeUserSession(String id){
        users.remove(id);
    }
    public static Session getAdmin() {
        return admin;
    }

    public static void setAdmin(Session admin) {
        SocketManager.admin = admin;
    }
}
