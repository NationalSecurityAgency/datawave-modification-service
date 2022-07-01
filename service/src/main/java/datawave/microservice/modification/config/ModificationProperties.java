package datawave.microservice.modification.config;

public class ModificationProperties {
    
    private String queryHost;
    
    private int queryPort;
    
    public String getQueryHost() {
        return queryHost;
    }
    
    public void setQueryHost(String queryHost) {
        this.queryHost = queryHost;
    }
    
    public int getQueryPort() {
        return queryPort;
    }
    
    public void setQueryPort(int queryPort) {
        this.queryPort = queryPort;
    }
}
