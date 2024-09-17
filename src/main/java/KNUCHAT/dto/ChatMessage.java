package KNUCHAT.dto;

import KNUCHAT.type.ChatMessageType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;

public record ChatMessage(
        Long roomId,
        Long senderId,
        Long receiverId,
        String message,
        LocalDateTime sendTime,
        ChatMessageType chatMessageType
) {
    public static ChatMessage from(VideoMessage videoMessage){
        return new ChatMessage(
                videoMessage.roomId(),
                videoMessage.senderId(),
                videoMessage.receiverId(),
                videoMessage.videoCallStatus().getMessage(),
                LocalDateTime.now(),
                ChatMessageType.NOTICE
        );
    }
}