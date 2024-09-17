package KNUCHAT.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "socket")
public record SocketProperties(
        String stompEndpoint,
        String[] allowedOrigins,
        String simpleBroker,
        String[] applicationDestinationPrefixes
) {
}
