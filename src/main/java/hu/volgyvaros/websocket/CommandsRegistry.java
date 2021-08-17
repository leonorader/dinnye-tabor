package hu.volgyvaros.websocket;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Log
@Component
public class CommandsRegistry {

    private Map<String, Map<String, String>> sessions = new HashMap<>(); // name, command, value

    public void registerCommand(String name, String command) {
        log.info("register name " + name + " command " + command);
        if (sessions.containsKey(name)) {
            sessions.get(name).put(command, null);
        } else {
            sessions.put(name, new HashMap<String, String>() {{
                put(command, null);
            }});
        }
    }

    // websocket message set
    public void setResponse(String name, String command, String value) {
        log.info("setting response: name " + name + " command " + command + " value " + value);
        if (sessions.containsKey(name) && sessions.get(name).containsKey(command)) {
            sessions.get(name).put(command, value);
        }
    }

    // rest api
    public String getResponse(String name, String command) {
        log.info("getting response: name " + name + " command " + command);
        if (sessions.containsKey(name) && sessions.get(name).containsKey(command)) {
            String response = sessions.get(name).get(command);
            sessions.get(name).remove(command);
            return response;
        }
        return null;
    }

    // rest api
    public boolean hasResponse(String name, String command) {
        return sessions.containsKey(name) && sessions.get(name).containsKey(command) && sessions.get(name).get(command) != null;
    }

}
