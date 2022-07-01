package datawave.microservice.modification;

import datawave.microservice.authorization.user.ProxiedUserDetails;
import datawave.microservice.modification.config.ModificationProperties;
import datawave.microservice.query.QueryClient;
import datawave.modification.query.ModificationQueryService;
import datawave.query.exceptions.DatawaveQueryException;
import datawave.security.authorization.JWTTokenHandler;
import datawave.webservice.result.BaseQueryResponse;
import datawave.webservice.result.GenericResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

public class RemoteQueryService implements ModificationQueryService {
    
    private final ProxiedUserDetails user;
    private final ProxiedUserDetails trustedUser;
    private final ModificationProperties properties;
    
    // private final AuditParameterBuilder
    
    private final QueryClient queryClient;
    
    private RemoteQueryService(ModificationProperties properties, QueryClient queryClient, ProxiedUserDetails user, ProxiedUserDetails trustedUser) {
        this.properties = properties;
        this.queryClient = queryClient;
        this.user = user;
        this.trustedUser = trustedUser;
    }
    
    public static RemoteQueryService create(ModificationProperties properties, JWTTokenHandler handler, ProxiedUserDetails user,
                    ProxiedUserDetails trustedUser) {
        return new RemoteQueryService(properties, QueryClient.create(properties.getQueryHost(), properties.getQueryPort(), handler), user, trustedUser);
    }
    
    @Override
    public GenericResponse<String> createQuery(String logicName, Map<String,List<String>> paramsToMap) throws DatawaveQueryException {
        return queryClient.createQuery(user, trustedUser, logicName, toMultiValueMap(paramsToMap));
    }
    
    public static MultiValueMap<String,String> toMultiValueMap(Map<String,List<String>> mapOfList) {
        MultiValueMap<String,String> multiValueMap = new LinkedMultiValueMap<>();
        mapOfList.entrySet().forEach(e -> multiValueMap.addAll(e.getKey(), e.getValue()));
        return multiValueMap;
    }
    
    @Override
    public BaseQueryResponse next(String id) throws DatawaveQueryException {
        return queryClient.next(user, trustedUser, id);
    }
    
    @Override
    public void close(String id) throws DatawaveQueryException {
        queryClient.close(user, trustedUser, id);
    }
}
