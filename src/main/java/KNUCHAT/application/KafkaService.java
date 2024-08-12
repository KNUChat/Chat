package KNUCHAT.application;

import KNUCHAT.domain.ChatMessage;
import KNUCHAT.domain.VideoMessage;
import KNUCHAT.type.ChatMessageType;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaService {

    private final KafkaTemplate<Object, Object> kafkaTemplate;
    private final SimpMessagingTemplate stompTemplate;

    public void sendMessage(ChatMessage chatMessage){
        log.info("Producer message : " + chatMessage);
        this.kafkaTemplate.sendDefault(chatMessage);
    }

    @KafkaListener(topics = "connect-video-call-room", errorHandler = "kafkaListenerErrorHandler")
    public void sendVideoMessage(VideoMessage videoMessage){
        log.info("VideoCall Message : " + videoMessage);
        ChatMessage chatMessage = ChatMessage.builder()
                .roomId(videoMessage.getRoomId())
                .receiverId(videoMessage.getReceiverId())
                .senderId(videoMessage.getSenderId())
                .chatMessageType(ChatMessageType.NOTICE)
                .message(videoMessage.getVideoCallStatus().getMessage())
                .sendTime(LocalDateTime.now())
                .build();
        this.sendMessage(chatMessage);
        stompTemplate.convertAndSend("/sub/room/" + videoMessage.getRoomId().toString(),
                chatMessage);
    }
}
