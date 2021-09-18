package com.microservice.bookcatalogue.kafka.controller;

import com.microservice.bookcatalogue.kafka.service.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bookCatalogue-kafka/")
public class KafkaController {

    @Autowired
    KafkaSender kafkaSender;

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("event") String event) {
        kafkaSender.send(event);

        return "Message sent to the Kafka Topic book-catalogue_topic Successfully";
    }
}
