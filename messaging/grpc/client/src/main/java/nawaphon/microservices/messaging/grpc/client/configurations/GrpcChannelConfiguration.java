package nawaphon.microservices.messaging.grpc.client.configurations;

import nawaphon.microservices.messaging.grpc.client.MainServerGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;


@Configuration
class GrpcChannelConfiguration {


    @Bean
    public MainServerGrpc.MainServerBlockingV2Stub mainServerBlockingV2Stub(GrpcChannelFactory channels) {
        return MainServerGrpc.newBlockingV2Stub(channels.createChannel("default-channel"));
    }

}
