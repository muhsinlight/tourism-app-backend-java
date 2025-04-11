package com.teknofest.turizm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaAuditing
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeknofestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeknofestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
