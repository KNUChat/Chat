package KNUCHAT.Controller;

import KNUCHAT.Domain.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MessageHandlingController {
    @MessageMapping("/hello") // pub
    @SendTo("/sub/greeting") // Sub
    public ChatMessage testMessage(ChatMessage chatMessage) throws Exception{
        log.info(chatMessage.getName());

        return chatMessage;
    }

}
