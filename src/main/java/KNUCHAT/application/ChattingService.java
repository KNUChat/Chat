package KNUCHAT.application;

import KNUCHAT.config.SocketProperties;
import KNUCHAT.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChattingService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final SocketProperties socketProperties;

    public void sendMessage(Long id, ChatMessage message){
        simpMessagingTemplate.convertAndSend(socketProperties.simpleBroker() + "/" + id, message);
    }
}
