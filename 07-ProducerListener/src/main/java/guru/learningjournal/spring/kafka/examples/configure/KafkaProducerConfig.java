package guru.learningjournal.spring.kafka.examples.configure;

import guru.learningjournal.spring.kafka.examples.models.User;
import guru.learningjournal.spring.kafka.examples.services.ProducerListenerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Log4j2
@Configuration
public class KafkaProducerConfig {

    @Autowired
    private ProducerListenerService producerListener;

    @Bean
    public KafkaTemplate<Integer, User> kafkaTemplate(ProducerFactory<Integer, User> pf) {
        KafkaTemplate<Integer, User> kafkaTemplate = new KafkaTemplate<>(pf);
        kafkaTemplate.setDefaultTopic("invoices");
        kafkaTemplate.setProducerListener(producerListener);
        return kafkaTemplate;
    }
}
