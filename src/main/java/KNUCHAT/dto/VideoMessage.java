package KNUCHAT.dto;

public record VideoMessage(
        Long roomId,
        Long senderId,
        Long receiverId,
        VideoCallStatus videoCallStatus
) {
}