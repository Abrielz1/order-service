package com.example.orderservice.model;

import lombok.Data;

@Data
public class KafkaMessage {

    private Long id;

    private String product;

    private Long quantity;
}
