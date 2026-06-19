package nawaphon.microservices.messaging.grpc.client.configurations;

import nawaphon.microservices.messaging.grpc.client.MainServerServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;


@Configuration
class GrpcChannelConfiguration {


    @Bean
    public MainServerServiceGrpc.MainServerServiceBlockingV2Stub mainServerBlockingV2Stub(GrpcChannelFactory channels) {
        return MainServerServiceGrpc.newBlockingV2Stub(channels.createChannel("default-channel"));
    }

}
