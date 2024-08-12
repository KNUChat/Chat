package KNUCHAT.presentation;

import KNUCHAT.domain.ChatMessage;
import KNUCHAT.application.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageHandlingController {
    private final KafkaService kafkaService;
    @MessageMapping("/{id}") // pub
    @SendTo("/sub/room/{id}") // Sub
    public ChatMessage testMessage(@DestinationVariable("id") Long id,
                                   ChatMessage chatMessage){
        log.info(chatMessage.getMessage());
        kafkaService.sendMessage(chatMessage);

        return chatMessage;
    }

}
