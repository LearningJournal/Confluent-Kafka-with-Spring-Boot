package guru.learningjournal.spring.kafka.examples.service;

import guru.learningjournal.spring.kafka.examples.models.Discount;
import guru.learningjournal.spring.kafka.examples.models.Invoice;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Log4j2
@Service
public class KafkaDiscountRequesterService {

    @Value("${app.kafka.request-topic}")
    private String topic;

    @Autowired
    private ReplyingKafkaTemplate<String, Invoice, Discount> kafkaTemplate;

    public Discount sendMessage(Invoice invoice) throws InterruptedException, ExecutionException, TimeoutException {

        ProducerRecord<String, Invoice> producerRecord = new ProducerRecord<>(topic,
                invoice.getInvoiceNumber(), invoice);
        ConsumerRecord<String, Discount> consumerRecord = kafkaTemplate.sendAndReceive(producerRecord)
                .get(30, TimeUnit.MINUTES);

        return consumerRecord.value();
    }
}
