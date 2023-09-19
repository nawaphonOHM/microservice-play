package nawaphon.microservices.data_per_services.private_table_per_service.services;

import nawaphon.microservices.data_per_services.private_table_per_service.pojo.ResponseMessage;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    public MainService(){

    }



    public ResponseMessage firstService() {
        return new ResponseMessage("hello world");
    }
}
