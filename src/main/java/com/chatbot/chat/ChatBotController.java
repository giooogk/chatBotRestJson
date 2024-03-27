package com.chatbot.chat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatBotController {
    private final ChatBotService chatBotService;

    public ChatBotController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String message) {
        return chatBotService.processMessage(message);
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "ChatBot on fireee!";
    }
}
