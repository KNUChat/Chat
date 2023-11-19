package KNUCHAT.Controller;

import KNUCHAT.Domain.ChatMessage;
import KNUCHAT.Service.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageHandlingController {
    private final KafkaService kafkaService;
    @MessageMapping("/{id}") // pub
    @SendTo("/sub/room/{id}") // Sub
    public ChatMessage testMessage(@DestinationVariable("id") Long id,
                                   ChatMessage chatMessage) throws Exception{
        log.info(chatMessage.getMessage());
        kafkaService.sendMessage(chatMessage);

        return chatMessage;
    }

}
