package com.example.orderservice.service;

import com.example.orderservice.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageService {

    private final List<KafkaMessage> messages = new ArrayList<>();

    public void add(KafkaMessage message) {
        messages.add(message);
    }
}
