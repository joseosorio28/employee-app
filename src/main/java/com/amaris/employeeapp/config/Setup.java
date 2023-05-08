package com.amaris.employeeapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Setup {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
