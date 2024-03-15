package nawaphon.microservices.messaging.rest.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@ConfigurationProperties(prefix = "connection")
@ConfigurationPropertiesScan
public class ConnectionConfiguration {
    private String friendService;

    public String getFriendService() {
        return friendService;
    }

    public void setFriendService(String friendService) {
        this.friendService = friendService;
    }
}
