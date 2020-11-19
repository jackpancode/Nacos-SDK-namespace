package com.jack.nacosnamespace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplater {

    @Bean
    //@LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
