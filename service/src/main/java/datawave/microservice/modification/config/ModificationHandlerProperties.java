package datawave.microservice.modification.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "datawave.modification.handlers")
@Validated
public class ModificationHandlerProperties {
    List<String> authorizedRoles = new ArrayList<>();
    
    @NotBlank
    String eventTableName;
    @NotBlank
    String metadataTableName;
    @NotBlank
    String indexTableName;
    @NotBlank
    String reverseIndexTableName;
    
    List<String> securityMarkingExemptFields = new ArrayList<>();
    boolean requiresAudit = false;
    Map<String,String> indexOnlyMap = new HashMap<>();
    List<String> indexOnlySuffixes = new ArrayList<>();
    List<String> contentFields = new ArrayList<>();
}
