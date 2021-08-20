package hu.volgyvaros.task;

import hu.volgyvaros.websocket.BoardsSessionRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Slf4j
@Log
@Service
@RequiredArgsConstructor
public class WebsocketAliveKeeper {

    private final BoardsSessionRegistry registry;

    /**
     * Schedules task to invalidate old password reset tokens.
     */
    @Scheduled(cron = "${task.websocket.cron}")
    public void execute() throws IOException {
        log.info("SCHEDULED TASK - START - websocket connections keep alive.");

        registry.broadcast();

        log.info("SCHEDULED TASK - END - websocket connections keep alive.");
    }
}

