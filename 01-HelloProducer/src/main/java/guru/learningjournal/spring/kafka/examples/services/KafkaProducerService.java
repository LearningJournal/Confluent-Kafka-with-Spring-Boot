package guru.learningjournal.spring.kafka.examples.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class KafkaProducerService {

    @Value("${topic.name}")
    private String TOPIC;

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    public void sendMessage(Integer key, String value) {
        log.info(String.format("Producing Message- Key: %d, Value: %s", key, value));
        kafkaTemplate.send(TOPIC, key, value);

    }
}
