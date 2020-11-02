package guru.learningjournal.spring.kafka.examples.service;

import guru.learningjournal.spring.kafka.examples.models.Discount;
import guru.learningjournal.spring.kafka.examples.models.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(id = "discount-server", topics = "discount-request", groupId = "request-listener-group-1")
    @SendTo
    public Message<Discount> KafkaConsumer(Invoice invoice) {

        log.info(String.format("Received Discount Request for Invoice : %s", invoice.getInvoiceNumber()));
        Discount discount = new Discount();
        discount.setInvoiceNumber(invoice.getInvoiceNumber());
        discount.setDiscount(invoice.getTotalAmount() * 0.10);
        return MessageBuilder.withPayload(discount)
                .setHeader(KafkaHeaders.MESSAGE_KEY, invoice.getInvoiceNumber())
                .build();
    }
}
