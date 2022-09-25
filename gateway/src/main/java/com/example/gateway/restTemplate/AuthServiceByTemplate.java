package com.example.gateway.restTemplate;

import com.example.common.R;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class AuthServiceByTemplate {

    public R byTemplate(@RequestParam Map<String, Object> params){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8081/auth/hasPermission", params, R.class);
    }

}
