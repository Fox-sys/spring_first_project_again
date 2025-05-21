package org.example.first_pr.adapters.api.v1.greeting;

import org.example.first_pr.adapters.api.v1.greeting.dtos.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/greeting")
public class GreetingController {
    @GetMapping
    public Message sayHi() {
        return new Message(200, "Привет");
    }
}
