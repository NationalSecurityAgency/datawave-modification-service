package datawave.microservice.modification.config;

import datawave.core.common.connection.AccumuloConnectionFactory;
import datawave.microservice.modification.cache.CacheReloadMonitor;
import datawave.modification.cache.ModificationCache;
import datawave.modification.configuration.ModificationConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableConfigurationProperties({ModificationQueryProperties.class, ModificationDataProperties.class, ModificationHandlerProperties.class})
public class ModificationCacheConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public ModificationCache modificationCache(AccumuloConnectionFactory connectionFactory, ModificationConfiguration modificationConfiguration) {
        return new ModificationCache(connectionFactory, modificationConfiguration);
    }
    
    @Bean
    @ConditionalOnMissingBean(CacheReloadMonitor.class)
    @ConditionalOnProperty(name = "datawave.modification.cache.monitor.enabled", havingValue = "true", matchIfMissing = true)
    public CacheReloadMonitor cacheReloadMonitor(ModificationCache modificationCache) {
        return new CacheReloadMonitor(modificationCache);
    }
    
}
