package com.sasadara.gradingapplication.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.sasadara.gradingapplication"})
@EntityScan(basePackages = "com.sasadara.gradingapplication.dbadapterjpa")
@EnableJpaRepositories("com.sasadara.gradingapplication.dbadapterjpa")
@EnableSpringDataWebSupport
public class GradingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GradingApplication.class, args);
    }

}
