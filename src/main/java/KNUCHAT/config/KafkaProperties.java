package KNUCHAT.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.kafka")
public record KafkaProperties(
        String bootstrapServers,
        String chatLogTopicId,
        String videoLogTopicId
) {
}
