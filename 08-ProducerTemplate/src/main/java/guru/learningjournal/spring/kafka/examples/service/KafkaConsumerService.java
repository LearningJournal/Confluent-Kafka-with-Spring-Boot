package guru.learningjournal.spring.kafka.examples.service;

import guru.learningjournal.spring.kafka.examples.DeliveryNotification;
import guru.learningjournal.spring.kafka.examples.models.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @Autowired
    private LogProducerService logProducerService;

    @Autowired
    private DeliveryEventProducerService deliveryEventProducerService;

    @KafkaListener(topics = "invoices", groupId = "invoice-reader-group6")
    public void KafkaConsumer(ConsumerRecord<String, Invoice> consumerRecord) {

        String log_entry = String.format("Received Invoice: %s at Offset: %d",
                consumerRecord.value().getInvoiceNumber(),
                consumerRecord.offset());

        logProducerService.sendMessage(log_entry);

        DeliveryNotification notification = new DeliveryNotification();
        notification.setInvoiceNumber(consumerRecord.value().getInvoiceNumber());
        notification.setDeliveryType(consumerRecord.value().getDeliveryType());

        deliveryEventProducerService.sendMessage(notification);

    }
}
