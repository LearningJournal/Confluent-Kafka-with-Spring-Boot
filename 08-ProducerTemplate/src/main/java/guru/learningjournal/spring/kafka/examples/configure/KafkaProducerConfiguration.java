package guru.learningjournal.spring.kafka.examples.configure;

import guru.learningjournal.spring.kafka.examples.DeliveryNotification;
import guru.learningjournal.spring.kafka.examples.models.Invoice;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Configuration
public class KafkaProducerConfiguration {

    @Bean
    public KafkaTemplate<String, String> kafkaLogTemplate(ProducerFactory<String, String> pf){
        return new KafkaTemplate<>(pf);
    }

    @Bean
    public KafkaTemplate<String, DeliveryNotification> kafkaNotificationTemplate(
            ProducerFactory<String, DeliveryNotification> pf){

        Map<String, Object> configs = new HashMap<>(pf.getConfigurationProperties());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroSerializer");

        return new KafkaTemplate<>(pf, configs);
    }

}
