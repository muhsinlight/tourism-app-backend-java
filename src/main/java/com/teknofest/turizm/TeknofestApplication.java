package com.teknofest.turizm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TeknofestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeknofestApplication.class, args);
    }

}
