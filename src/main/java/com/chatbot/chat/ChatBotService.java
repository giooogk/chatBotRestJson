package com.chatbot.chat;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ChatBotService {
    private final Bot bot;
    private final Chat chatSession;

    public ChatBotService() {
        String resourcesPath = getResourcesPath();
        bot = new Bot("super", resourcesPath);
        chatSession = new Chat(bot);
    }

    public String processMessage(String message) {
        try {
            if (message == null || message.isEmpty()) {
                message = "null_input";
            }

            String response = chatSession.multisentenceRespond(message);

            while (response.contains("&lt;"))
                response = response.replace("&lt;", "<");
            while (response.contains("&gt;"))
                response = response.replace("&gt;", ">");

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao processar a mensagem: " + e.getMessage();
        }
    }

    private String getResourcesPath() {
        String resourcesPath = "";
        try {
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            path = path.substring(0, path.length() - 2);
            resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resourcesPath;
    }
}
