package KNUCHAT.Config;


import KNUCHAT.Domain.VideoMessage;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@PropertySource("classpath:application-kafka.properties")
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${spring.kafka.producer.topic}")
    private String producerDefaultTopic;

    @Value("${spring.kafka.consumer.group-id}")
    private String consumerDefaultGroupId;
    //ProducerFactory
    @Bean
    public ProducerFactory<Object, Object> producerFactory(){
        Map<String, Object> props = new HashMap<>();

        //Customized
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);

        //Default
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    //Default Produce Topic
    @Bean
    public KafkaTemplate<Object, Object> kafkaTemplate(ProducerFactory<Object,Object> producerFactory){
        KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<>(producerFactory);

        //Customize
        kafkaTemplate.setDefaultTopic(producerDefaultTopic);

        return kafkaTemplate;
    }

    @Bean
    public ConsumerFactory<Object, Object> consumerFactory() {
//
//        JsonDeserializer<VideoMessage> deserializer = new JsonDeserializer<>(VideoMessage.class);
//        deserializer.setRemoveTypeHeaders(false);
//        deserializer.addTrustedPackages("*");
//        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object> props = new HashMap<>();

        //Customized
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerDefaultGroupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, "true");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, VideoMessage.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }
}
