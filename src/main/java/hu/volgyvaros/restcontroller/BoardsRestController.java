package hu.volgyvaros.restcontroller;

import hu.volgyvaros.websocket.BoardsSessionRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Log
@RestController
@RequestMapping("/api/boards/")
@RequiredArgsConstructor
public class BoardsRestController {

    /*--------------- FORWARD ALL ------------------*/

    private final BoardsSessionRegistry registry;

    @GetMapping("/{name}/command/**")
    public String boards(HttpServletRequest request, @PathVariable String name) throws IOException {
        String requestURL = request.getRequestURL().toString();
        String command = requestURL.split("/command/")[1];
        registry.sendCommand(name, command);
        try {
            TimeUnit.SECONDS.sleep(150);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        return "name is " + name + " command is: " + command;
    }

    @GetMapping("/{name}/response")
    public String response(HttpServletRequest request, @PathVariable String name) {
        return registry.getResponse(name);
    }

}
