package controller.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.interfaces.ISeatDAO;
import jakarta.inject.Inject;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.server.ServerEndpointConfig;

import java.io.IOException;

@ServerEndpoint(value = "/{username}", configurator = WebSocket.MyConfigurator.class)
public class WebSocket {

    @Inject
    private ISeatDAO iSeatDAO;


    @OnOpen
    public void onOpen(
            Session session,
            @PathParam("username") String username) throws IOException {
        String query = session.getRequestURI().getQuery();

        SocketManager.setUserSession(username, session);
        String uri = session.getRequestURI().toString();
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(SocketManager.openUrls));
        boolean isExist = SocketManager.openUrls.contains(uri);
        System.out.println(uri);

    }

    @OnMessage
    public void onMessage(Session session, String message)
            throws IOException {
        System.out.println(message);
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String userId) throws IOException {
        System.out.println(session.getId());
        SocketManager.removeUserSession(userId);

    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    public void emitMessage(String id, String message) {
        Session session = SocketManager.getUser(id);
        if (session == null) return;
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class MyConfigurator extends ServerEndpointConfig.Configurator {
        // Implement any custom configuration here if needed
    }
}
