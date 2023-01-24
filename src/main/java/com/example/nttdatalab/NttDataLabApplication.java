package com.example.nttdatalab;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class NttDataLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(NttDataLabApplication.class, args);
    }

}
