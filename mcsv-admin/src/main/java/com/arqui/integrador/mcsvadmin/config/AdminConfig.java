package com.arqui.integrador.mcsvadmin.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AdminConfig {
    
    @Bean
    @LoadBalanced
	public RestTemplate restTemplateAdmin() { 
        return new RestTemplate();
    }
}