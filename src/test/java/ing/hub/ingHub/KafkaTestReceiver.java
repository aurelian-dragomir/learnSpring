package ing.hub.ingHub;

import lombok.Getter;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

@Getter
public class KafkaTestReceiver {
    private String message;
//    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "out", groupId = "group2")
    public void listen(String msg) {
        message = msg;
//        latch.countDown();
    }
}
