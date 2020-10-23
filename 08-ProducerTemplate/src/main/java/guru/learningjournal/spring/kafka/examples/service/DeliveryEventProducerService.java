package guru.learningjournal.spring.kafka.examples.service;

import guru.learningjournal.spring.kafka.examples.DeliveryNotification;
import guru.learningjournal.spring.kafka.examples.models.Invoice;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class DeliveryEventProducerService {
    @Autowired
    private KafkaTemplate<String, DeliveryNotification> kafkaTemplate;

    public void sendMessage(DeliveryNotification deliveryNotification) {

        log.info(String.format("Sending Delivery Notification : %s", deliveryNotification.getInvoiceNumber()));
        kafkaTemplate.send("delivery-event", deliveryNotification);
    }
}
