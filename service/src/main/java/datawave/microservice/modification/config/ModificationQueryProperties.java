package datawave.microservice.modification.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@ConfigurationProperties(prefix = "datawave.modification.query")
@Validated
public class ModificationQueryProperties {
    
    @NotBlank
    private String queryHost;
    
    @Positive
    private int queryPort;
    
    @NotBlank
    private String queryPool;
    
    @Positive
    protected long remoteQueryTimeout = 1L;
    
    @NotNull
    protected TimeUnit remoteQueryTimeUnit = TimeUnit.MINUTES;
    
    @NotBlank
    private String authHost;
    
    @Positive
    private int authPort;
    
    public String getQueryServiceUri() {
        return "https://" + queryHost + ':' + queryPort + '/';
    }
    
    public String getAuthServiceUri() {
        return "https://" + authHost + ':' + authPort + '/';
    }
    
    public long getRemoteQueryTimeoutMillis() {
        return remoteQueryTimeUnit.toMillis(remoteQueryTimeout);
    }
    
}
