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

    private final CommandsRegistry commandsRegistry;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("new session opened: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        JSONObject parsedMessage = new JSONObject(message.getPayload());
        String id = parsedMessage.getString("id");

        switch (id) {
            case "register":
                registerSession(parsedMessage.getString("name"), session);
                break;
            case "response":
                setResponse(parsedMessage.getString("name"), parsedMessage.getString("command"), parsedMessage.getString("value"));
                break;

                default: break;
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        registry.removeBySessionId(session.getId());
        log.info("session closed: " + session.getId());
    }

    private void registerSession(String name, WebSocketSession session) {
        registry.register(name, session);
    }

    private void setResponse(String name, String command, String value) {
        commandsRegistry.setResponse(name, command, value);
    }

}
