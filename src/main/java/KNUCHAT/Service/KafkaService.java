package KNUCHAT.Service;

import KNUCHAT.Domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaService {

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public void sendMessage(ChatMessage chatMessage){
        log.info("Producer message : " + chatMessage.getMessage());
        this.kafkaTemplate.sendDefault(chatMessage.getMessage());
    }
}
