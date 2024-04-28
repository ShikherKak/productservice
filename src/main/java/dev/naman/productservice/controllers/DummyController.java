package dev.naman.productservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DummyController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hi")
    public String sayHi()
    {
        String answer = restTemplate.getForObject("http://userservice/hi",String.class);
        return answer;
    }

}
