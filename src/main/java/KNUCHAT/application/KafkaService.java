package KNUCHAT.application;

import KNUCHAT.config.KafkaProperties;
import KNUCHAT.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {
    private final KafkaProperties kafkaProperties;
    private final KafkaTemplate<Object, Object> kafkaTemplate;
    private final SimpMessagingTemplate stompTemplate;

    public void sendMessage(ChatMessage chatMessage){
        log.info("Producer message : " + chatMessage);
        this.kafkaTemplate.sendDefault(chatMessage);
    }
}
