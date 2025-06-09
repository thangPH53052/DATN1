// ChatLieuService.java
package org.example.datn.Service;

import org.example.datn.Entity.ChatLieu;
import org.example.datn.Repository.ChatLieuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatLieuService {
    private final ChatLieuRepository chatLieuRepository;

    public ChatLieuService(ChatLieuRepository chatLieuRepository) {
        this.chatLieuRepository = chatLieuRepository;
    }

    public List<ChatLieu> getAllChatLieu() {
        return chatLieuRepository.findAll();
    }
}
