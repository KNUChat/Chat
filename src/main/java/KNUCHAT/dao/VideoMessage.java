package KNUCHAT.dao;

import KNUCHAT.enumType.VideoCallStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class VideoMessage {
    private Long roomId;
    private Long senderId;
    private Long receiverId;
    private VideoCallStatus videoCallStatus;
}