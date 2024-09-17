package KNUCHAT.application;

import KNUCHAT.common.Facade;
import KNUCHAT.dto.ChatMessage;
import lombok.RequiredArgsConstructor;

@Facade
@RequiredArgsConstructor
public class ChattingFacade {
    private final ChattingService chattingService;
    private final KafkaService kafkaService;

    public void sendMessage(Long id, ChatMessage message){
        chattingService.sendMessage(id, message);
        kafkaService.sendMessage(message);
    }
}
