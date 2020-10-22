package guru.learningjournal.spring.kafka.examples.services;

import guru.learningjournal.spring.kafka.examples.models.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<Integer, User> kafkaTemplate;

    public void sendMessage(Integer key, User value) {
        log.info(String.format("Producing Message- Key: %d Value: %s", key, value));
        kafkaTemplate.sendDefault(key, value);

    }
}
