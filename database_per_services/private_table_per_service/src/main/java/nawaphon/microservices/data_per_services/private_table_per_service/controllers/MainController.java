package nawaphon.microservices.data_per_services.private_table_per_service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
@ResponseBody
public class MainController {

    static public class ResponseMessage {
        private final String message;

        public ResponseMessage(final String message){
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public MainController() {

    }


    @GetMapping("/hello-world")
    public ResponseMessage firstGetMethod(){
        return new ResponseMessage("hello world");
    }
}