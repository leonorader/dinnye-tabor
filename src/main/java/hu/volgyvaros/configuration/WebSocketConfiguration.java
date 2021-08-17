package hu.volgyvaros.configuration;

import hu.volgyvaros.websocket.BoardsHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@Controller
@RequiredArgsConstructor
public class WebSocketConfiguration implements WebSocketConfigurer {


    private final BoardsHandler boardsHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(boardsHandler, "/boards").setAllowedOrigins("*");
    }
}
