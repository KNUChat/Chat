package KNUCHAT.presentation;

import KNUCHAT.application.ChattingFacade;
import KNUCHAT.dto.ChatMessage;
import KNUCHAT.dto.VideoMessage;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageHandlingController {
    private final ChattingFacade chattingFacade;

    @MessageMapping("/{id}") // pub
    public void mapChatMessage(@DestinationVariable Long id,
                               ChatMessage chatMessage
    ){
        log.info("Chat Message : " + chatMessage);
        chattingFacade.sendMessage(id, chatMessage);
    }

    @KafkaListener(groupId = "video-message-group", topics = "connect-video-call-room", errorHandler = "kafkaListenerErrorHandler")
    public void consumeVideoMessage(VideoMessage videoMessage){

        log.info("VideoCall Message : " + videoMessage);

        var chatMessage = ChatMessage.from(videoMessage);
        chattingFacade.sendMessage(chatMessage.roomId(), chatMessage);
    }
}