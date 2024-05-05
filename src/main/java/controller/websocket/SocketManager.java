package controller.websocket;

import jakarta.websocket.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SocketManager {

    private static final HashMap<String, Session> users = new HashMap<>();
    public static List<String> openUrls = new ArrayList<>();
    private static Session admin;

    public static HashMap<String, Session> getUserSessions() {
        return users;
    }

    public static void setUserSession(String id, Session session) {
        users.put(id, session);
    }

    public static void removeUserSession(String id) {
        users.remove(id);
    }

    public static Session getAdmin() {
        return admin;
    }

    public static void setAdmin(Session admin) {
        SocketManager.admin = admin;
    }
}
