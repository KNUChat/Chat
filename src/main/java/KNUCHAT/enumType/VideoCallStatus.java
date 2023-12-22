package KNUCHAT.enumType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VideoCallStatus {
    CONNECTED("CONNECTED"),
    DISCONNECTED("DISCONNECTED");

    private final String message;
}
