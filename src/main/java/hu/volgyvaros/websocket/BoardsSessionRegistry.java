package hu.volgyvaros.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Log
@Component
public class BoardsSessionRegistry {

    private ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>(); // panel name, session
    private ConcurrentHashMap<String, String> sessionsById = new ConcurrentHashMap<>(); // session id, name
    private Map<String, String> results = new HashMap<>(); // name, value

    public void register(String name, WebSocketSession session) {
        sessions.put(name, session);
        sessionsById.put(session.getId(), name);
        results.put(name, null);
        log.info("register name " + name);
    }

    // websocket message set
    public void setResponse(String id, String value) {
        String name = sessionsById.get(id);
        log.info("setting response: name " + name + " value " + value);
        results.put(name, value);
    }

    // rest api
    public String getResponse(String name) {
        log.info("getting response: name " + name);
        return results.get(name);
    }

    public void sendCommand(String name, String command) throws IOException {
        if (sessions.containsKey(name)) {
            sessions.get(name).sendMessage(new TextMessage(command));
        }
    }

    public void broadcast() throws IOException {
        for (WebSocketSession session : sessions.values()) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage("ping"));
            } else {
                removeBySessionId(session.getId());
            }
        }
    }

    public void removeBySessionId(String sessionId) {
        String name = sessionsById.get(sessionId);
        sessions.remove(name);
        sessionsById.remove(sessionId);
    }

}
