package ing.hub.ingHub.component;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiver {

    @KafkaListener(topics = "hello", groupId = "my_group")
    public void listen(String message) {
        System.out.println("Received message " + message);
    }

}
