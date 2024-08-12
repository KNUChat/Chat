package KNUCHAT.domain;

import KNUCHAT.type.ChatMessageType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatMessage {
    private Long roomId;
    private Long senderId;
    private Long receiverId;
    private String message;
    private LocalDateTime sendTime;
    private ChatMessageType chatMessageType;
}