package guru.learningjournal.spring.kafka.examples;

import guru.learningjournal.spring.kafka.examples.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AvroProducerApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(AvroProducerApplication.class, args);
	}

	@Autowired
	private KafkaProducerService producerService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		producerService.sendMessage(new User("Prashant", 43));
	}
}
