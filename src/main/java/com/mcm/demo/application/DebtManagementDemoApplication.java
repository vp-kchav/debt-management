package com.mcm.demo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EntityScan("com.mcm.demo.adapter.persistence.entity")
@EnableJpaRepositories("com.mcm.demo")
public class DebtManagementDemoApplication
{
    public static void main(String[] args) throws Exception {
        SpringApplication.run(DebtManagementDemoApplication.class, args);
        
    }
    
}