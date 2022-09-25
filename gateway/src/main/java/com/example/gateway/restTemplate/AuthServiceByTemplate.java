package com.example.gateway.restTemplate;

import com.example.common.R;
import com.example.common.ServiceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class AuthServiceByTemplate {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    public R byTemplate(@RequestParam Map<String, Object> params){
        return restTemplate.postForObject("http://localhost:8081/auth/hasPermission", params, R.class);
    }

    public R byBalancer(@RequestParam Map<String, Object> params){
        ServiceInstance choose = loadBalancerClient.choose("SERVICE-AUTH");
        String host = choose.getHost();
        Integer port = choose.getPort();
        String url = String.format("http://%s:%d/auth/hasPermission",host,port);
        return restTemplate.postForObject(url, params, R.class);
    }

    public R byBalancerBetter(@RequestParam Map<String, Object> params){
        // restTemplate对象必须要有@LoadBalanced注解，否则调用会提示找不到服务
        String url = String.format("http://%s/auth/hasPermission", ServiceName.SERVICE_AUTH);
        return restTemplate.postForObject(url, params, R.class);
    }

}
