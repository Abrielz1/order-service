package com.example.orderservice.web.controller;

import com.example.orderservice.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
@RequiredArgsConstructor
public class KafkaController {

    @Value("${app.kafka.kafkaMessageTopic")
    private String topicName;

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    @PostMapping("/send")
     @ResponseStatus(HttpStatus.OK)
     public String sendMessage(@RequestBody KafkaMessage message) {
     kafkaTemplate.send(topicName, message);
     return "Message were send to kafka";
     }
}
