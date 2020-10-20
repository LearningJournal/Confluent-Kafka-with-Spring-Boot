package guru.learningjournal.spring.kafka.examples;

import guru.learningjournal.spring.kafka.examples.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloProducerApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(HelloProducerApplication.class, args);
    }

    @Autowired
    private KafkaProducerService producerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
            producerService.sendMessage(1, "{\"name\": \"Prashant\", \"age\": 43}" );
    }
}
