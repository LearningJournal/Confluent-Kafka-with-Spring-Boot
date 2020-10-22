package guru.learningjournal.spring.kafka.examples.services;

import guru.learningjournal.spring.kafka.examples.models.User;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ProducerListenerService implements ProducerListener<Integer, User> {

    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        log.info(String.format("Message %s persisted at Offset %d",
                producerRecord.value(), recordMetadata.offset()));
    }

    @Override
    public void onError(ProducerRecord producerRecord, Exception exception) {
        log.info("Inside Failure:" + producerRecord.value());
    }
}
