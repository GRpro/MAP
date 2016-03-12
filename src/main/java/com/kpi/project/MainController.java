package com.kpi.project;

import com.kpi.project.model.Graph;
import com.kpi.project.model.Greeting;
import com.kpi.project.model.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        //Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @MessageMapping("/process")
    public void startProcessing(Graph graph) {
        // handle start
    }

}
