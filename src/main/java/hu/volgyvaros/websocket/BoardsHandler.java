package hu.volgyvaros.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Log
@Component
@RequiredArgsConstructor
public class BoardsHandler extends TextWebSocketHandler {

    private final BoardsSessionRegistry registry;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("new session opened: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info("WS message: " + message.getPayload());

        if (message.getPayload().contains("register")) {
            JSONObject parsedMessage = new JSONObject(message.getPayload());
            registerSession(parsedMessage.getString("name"), session);
        } else {
            setResponse(session, message.getPayload());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("session closed: " + session.getId());
        log.info("session closed status: " + status.toString());
        registry.removeBySessionId(session.getId());
    }

    private void registerSession(String name, WebSocketSession session) {
        registry.register(name, session);
    }

    private void setResponse(WebSocketSession session, String value) {
        registry.setResponse(session.getId(), value);
    }

}
