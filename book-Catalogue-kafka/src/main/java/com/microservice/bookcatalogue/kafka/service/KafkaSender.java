package com.microservice.bookcatalogue.kafka.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    String kafkaTopic = "book-catalogue_topic";

    public void send(String message) {

        kafkaTemplate.send(kafkaTopic, message);
    }
}