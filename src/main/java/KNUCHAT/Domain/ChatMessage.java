package KNUCHAT.Domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatMessage {
    private Long roomId;
    private Long senderId;
    private Long receiverId;
    private String message;

    private LocalDateTime sendTime;}