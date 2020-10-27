package guru.learningjournal.spring.kafka.examples.config;

import guru.learningjournal.spring.kafka.examples.models.Discount;
import guru.learningjournal.spring.kafka.examples.models.Invoice;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaConfig {

    @Value("${app.kafka.reply-topic}")
    private String replyTopic;

    @Value("${app.kafka.reply-listener-group-id}")
    private String replyGroupID;

    @Bean
    ReplyingKafkaTemplate<String, Invoice, Discount> replyingKafkaTemplate(
            ProducerFactory<String, Invoice> pf,
            ConcurrentKafkaListenerContainerFactory<String, Discount> cf){
        ConcurrentMessageListenerContainer<String, Discount> container = cf.createContainer(replyTopic);
        container.getContainerProperties().setGroupId(replyGroupID);
        container.setAutoStartup(false);
        return new ReplyingKafkaTemplate<>(pf,container);
    }
}
