package com.example.orderservice.listener;

import com.example.orderservice.model.KafkaMessage;
import com.example.orderservice.service.KafkaMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageListener {

//    private final KafkaMessageService kafkaMessageService;

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    @KafkaListener(topics = "${app.kafka.topicToRead}",
                   groupId = "${app.kafka.kafkaMessageGroupId}",
                   containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory")
    public void listen(@Payload KafkaMessage message,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                       @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(value = KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(value = KafkaHeaders.RECEIVED_TIMESTAMP) Long timeStamp) {

        log.info("Received message: {}", message);
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timeStamp);

  //      kafkaMessageService.add(message);
        kafkaTemplate.send(topic, message);
    }

    @KafkaListener(topics = "${app.kafka.topicToWrite}",
            groupId = "${app.kafka.kafkaMessageGroupId}",
            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory")
    public void send(@Payload KafkaMessage message,
                     @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                     @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
                     @Header(value = KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                     @Header(value = KafkaHeaders.RECEIVED_TIMESTAMP) Long timeStamp) {

        log.info("Received message: {}", message);
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timeStamp);

        kafkaTemplate.send(topic, message);
    }
}
