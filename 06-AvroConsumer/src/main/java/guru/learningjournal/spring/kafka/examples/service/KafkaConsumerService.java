package guru.learningjournal.spring.kafka.examples.service;

import guru.learningjournal.spring.kafka.examples.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "users", groupId = "consumer-group-1")
    public void KafkaConsumer(@Payload User value,
                                 @Header(name = KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {

        log.info(String.format("Received Message Key: %s, Value: %s", key, value.toString()));
    }
}
