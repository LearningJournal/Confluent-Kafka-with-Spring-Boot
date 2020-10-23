package guru.learningjournal.spring.kafka.examples.service;

import guru.learningjournal.spring.kafka.examples.models.Invoice;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class LogProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String value) {
        log.info(String.format("Producing Log Entry for Invoice"));
        kafkaTemplate.send("app-logs",value);
    }

}
