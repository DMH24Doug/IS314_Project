package com.s11160663.prototype_v3.Controller;

import com.s11160663.prototype_v3.ChatBot.ChatBotRequest;
import com.s11160663.prototype_v3.ChatBot.ChatBotResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatbot")
public class ChatBotController {



    //TODO check post method and script on front end if message request and response is working well
    @PostMapping("/message")
    public ChatBotResponse handle(@RequestBody ChatBotRequest request) {
        String userMessage = request.getMessage();
        String botResponse = generateBotResponse(userMessage);
        return new ChatBotResponse(botResponse);

    }

    //TODO method trail, main implementation should be in ChatService
    private String generateBotResponse(String message) {
        // Simple logic for bot response
        if (message.contains("hello")) {
            return "Hi there! How can I help you today?";
        } else if (message.contains("bye")) {
            return "Goodbye! Have a nice day!";
        } else {
            return "I'm sorry, I didn't understand that.";
        }
    }
}
