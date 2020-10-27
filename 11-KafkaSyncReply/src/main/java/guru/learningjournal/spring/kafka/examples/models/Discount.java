package guru.learningjournal.spring.kafka.examples.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discount {
    @JsonProperty("InvoiceNumber")
    private String invoiceNumber;
    @JsonProperty("Discount")
    private Double discount;
}
