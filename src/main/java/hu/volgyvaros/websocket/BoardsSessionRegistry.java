package hu.volgyvaros.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BoardsSessionRegistry {

    private ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>(); // session id

    /**
     * Returns if the registry has no registered sessions.
     *
     * @return if registry is empty
     */
    public boolean isEmpty() {
        return sessions.isEmpty();
    }

    /**
     * Returns the number of registered users.
     *
     * @return number of registered users
     */
    public int size() {
        return sessions.size();
    }

    /**
     * Registers a session to the registry.
     *
     * @param session session to register
     */
    public void register(String name, WebSocketSession session) {
        sessions.put(name, session);
    }

    /**
     * Finds a session by its id in the registry.
     *
     * @param sessionId session id to look for
     * @return session returns a session with the given id or null if not found
     */
    public WebSocketSession getBySessionId(String sessionId) {
        return sessions.get(sessionId);
    }

    /**
     * Broadcasts a message to all the sessions.
     *
     * @param message the message to broadcast
     * @throws IOException if message could not be sent
     */
    public void broadcast(JsonNode message) throws IOException {
        for (WebSocketSession session : sessions.values()) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message.toString()));
            } else {
                removeBySessionId(session.getId());
            }
        }
    }

    public void sendCommand(String name, String command) throws IOException {
        if (sessions.containsKey(name)) {
            sessions.get(name).sendMessage(new TextMessage(command));
        } else {

        }
    }

    /**
     * Removes a session from the registry by session id.
     *
     * @param sessionId session id to remove
     */
    public void removeBySessionId(String sessionId) {
        final WebSocketSession session = getBySessionId(sessionId);
        if (session != null) {
            sessions.remove(sessionId);
        }
    }

}
