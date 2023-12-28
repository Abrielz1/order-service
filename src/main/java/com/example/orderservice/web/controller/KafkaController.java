package com.example.orderservice.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
@RequiredArgsConstructor
public class KafkaController {

    //TODO:
    // PostMapping("/send")
    // @ResponseStatus(HttpStatus.OK)
    // public String sendMessage(@RequestBody KafkaMessage message) {
    // kafkaTemplate.send(topicName, message);
    // return "Message were send to kafka";
    // }
}
