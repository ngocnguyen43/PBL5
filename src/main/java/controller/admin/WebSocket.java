package controller.admin;

import controller.websocket.SocketManager;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;

@ServerEndpoint(value = "/chat/{username}")
public class WebSocket {
    private Session session;


    @OnOpen
    public void onOpen(
            Session session,
            @PathParam("username") String username) throws IOException {
        String query = session.getRequestURI().getQuery();

        this.session = session;
        SocketManager.setUserSession(session.getId(), session);
        session.getBasicRemote().sendText("OK");
    }

    @OnMessage
    public void onMessage(Session session, String message)
            throws IOException {
        System.out.println(message);
//        message.setFrom(users.get(session.getId()));
//        broadcast(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println(session.getId());
        SocketManager.removeUserSession(session.getId());

    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
}
