package guru.learningjournal.spring.kafka.examples.service;

import guru.learningjournal.spring.kafka.examples.DeliveryNotification;
import guru.learningjournal.spring.kafka.examples.models.Invoice;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.RoutingKafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class KafkaProducerService {

    @Autowired
    private RoutingKafkaTemplate kafkaTemplate;

    public void sendMessage(String topic, Object value) {
        if (topic.equals("delivery-event")){
            DeliveryNotification notification = (DeliveryNotification) value;
            log.info(String.format("Producing Notification: %s", notification.getInvoiceNumber()));
            kafkaTemplate.send(topic, notification.getInvoiceNumber(), notification);
        }else {
            String logEntry = (String) value;
            log.info(String.format("Producing Ino: %s", logEntry));
            kafkaTemplate.send(topic, logEntry);
        }
    }

}
