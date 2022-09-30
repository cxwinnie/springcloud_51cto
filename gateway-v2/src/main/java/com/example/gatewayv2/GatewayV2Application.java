package com.example.gatewayv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayV2Application {
    //http://127.0.0.1:9000/service-auth/auth/hasPermission?token=123
    public static void main(String[] args) {
        SpringApplication.run(GatewayV2Application.class, args);
    }

}
