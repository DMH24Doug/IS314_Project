package com.s11160663.prototype_v3.ChatBot;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class ChatBotResponse {
    // Getter
    private String reply;

    public ChatBotResponse(String reply) {
        this.reply = reply;
    }

}
