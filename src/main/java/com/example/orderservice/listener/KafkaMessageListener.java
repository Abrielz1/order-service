package com.example.orderservice.listener;

import com.example.orderservice.model.KafkaMessage;
import com.example.orderservice.service.KafkaMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageListener {

   private final KafkaMessageService kafkaMessageService;

    @KafkaListener(topics = "${app.kafka.topicToRead}",
                   groupId = "${app.kafka.kafkaMessageGroupId}",
                   containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory")
    public void listen(@Payload KafkaMessage message,
                       @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(value = KafkaHeaders.RECEIVED_PARTITION) String status,
                       @Header(value = KafkaHeaders.RECEIVED_TIMESTAMP) Long timeStamp) {

        log.info("Received message: {}", message);
        log.info("Message: {}; Topic: {}; Status: {}; Timestamp: {}", message, topic, status, timeStamp);

        kafkaMessageService.add(message);
        System.out.println("messages list has: " + kafkaMessageService.print());
    }
}
