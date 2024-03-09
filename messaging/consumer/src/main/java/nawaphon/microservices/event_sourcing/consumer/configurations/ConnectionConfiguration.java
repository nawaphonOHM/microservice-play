package nawaphon.microservices.event_sourcing.consumer.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "connection")
public class ConnectionConfiguration {
    private String friendService;

    public String getFriendService() {
        return friendService;
    }

    public void setFriendService(String friendService) {
        this.friendService = friendService;
    }
}
