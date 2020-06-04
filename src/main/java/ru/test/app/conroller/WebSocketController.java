package ru.test.app.conroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    @Autowired private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String plusOne(String input) {
        return "Hello, " + input;
    }

    @Scheduled(initialDelay = 2000, fixedDelay = 2000)
    private void sendPing() {
        //logger.info("Sending ping...");
        simpMessagingTemplate.convertAndSend("/topic/greetings", "Ping");
    }
}
