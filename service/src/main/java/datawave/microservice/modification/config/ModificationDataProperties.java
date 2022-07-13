package datawave.microservice.modification.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ConfigurationProperties(prefix = "datawave.modification.data")
@Validated
public class ModificationDataProperties {
    private String xmlBeansPath = "classpath:ModificationServices.xml";
}
