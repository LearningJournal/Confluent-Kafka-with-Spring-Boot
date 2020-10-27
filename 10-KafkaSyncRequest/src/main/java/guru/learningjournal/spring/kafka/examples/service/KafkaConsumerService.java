package guru.learningjournal.spring.kafka.examples.service;

import guru.learningjournal.spring.kafka.examples.models.Discount;
import guru.learningjournal.spring.kafka.examples.models.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class KafkaConsumerService {

    @Autowired
    KafkaDiscountRequesterService discountRequesterService;

    @KafkaListener(topics = "invoices", groupId = "invoice-consumer-group-1")
    public void KafkaConsumer(Invoice invoice) throws InterruptedException, ExecutionException, TimeoutException {

        Discount discount = discountRequesterService.sendMessage(invoice);
        log.info("Processed Invoice: " + invoice.getInvoiceNumber() + " and received : " + discount);

    }
}
