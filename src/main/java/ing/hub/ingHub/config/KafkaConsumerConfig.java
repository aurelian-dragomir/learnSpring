package ing.hub.ingHub.config;

import ing.hub.ingHub.model.PersonDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.transaction.KafkaTransactionManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.in-group-id")
    private String inGroupId;

    @Value(value = "${spring.kafka.out-group-id")
    private String outGroupId;

    @Autowired
    @Qualifier("personDetailsKafkaTransactionManager")
    private KafkaTransactionManager kafkaTransactionManager;

    @Bean
    public ConsumerFactory<Long, PersonDto> inConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, inGroupId);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProps.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        configProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "ing.hub.ingHub.model.PersonDetailsDto");

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, PersonDto> inContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, PersonDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(inConsumerFactory());
        factory.getContainerProperties().setKafkaAwareTransactionManager(kafkaTransactionManager);
        factory.setConcurrency(3);
        return factory;
    }

    @Bean
    public ConsumerFactory<Long, PersonDto> outConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, outGroupId);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        configProps.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "ing.hub.ingHub.model.PersonDto");
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, PersonDto> outContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, PersonDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(outConsumerFactory());
        factory.setConcurrency(3);
        return factory;
    }
}
