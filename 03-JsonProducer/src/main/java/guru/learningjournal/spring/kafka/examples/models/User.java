package guru.learningjournal.spring.kafka.examples.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @JsonProperty
    private String name;
    @JsonProperty
    private Integer age;
}
