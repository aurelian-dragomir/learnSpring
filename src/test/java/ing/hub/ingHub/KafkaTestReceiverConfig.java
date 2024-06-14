package ing.hub.ingHub;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTestReceiverConfig {

    @Bean
    public KafkaTestReceiver kafkaOutReceiver() {
        return new KafkaTestReceiver();
    }
}
