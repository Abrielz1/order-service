package com.example.orderservice.listener;

import com.example.orderservice.model.KafkaMessage;
import com.example.orderservice.model.KafkaMessageDTO;
import com.example.orderservice.service.KafkaMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageListener {

   private final KafkaMessageService kafkaMessageService;

    @KafkaListener(topics = "${app.kafka.topicToRead}",
                   groupId = "${app.kafka.kafkaMessageGroupId}",
                   containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory")
    public void receive(@Payload KafkaMessageDTO message,
                        @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic) {

        log.info("Received message: {}", message);
        log.info("Message: {}; Topic: {}, Time: {}", message, topic, System.currentTimeMillis());

        System.out.println("message: " + message + " time received in ms:" + System.currentTimeMillis());
        kafkaMessageService.addDTO(message);
    }
}
