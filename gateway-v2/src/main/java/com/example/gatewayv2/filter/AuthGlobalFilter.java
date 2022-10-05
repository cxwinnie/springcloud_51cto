package com.example.gatewayv2.filter;

import com.example.common.R;
import com.example.gatewayv2.feign.AuthServiceByFeign;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    AuthServiceByFeign authServiceByFeign;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("token");
        String url = exchange.getRequest().getURI().getPath();

        if (token != null && !token.isEmpty()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("token", token);
            R r = authServiceByFeign.byFeignPost(params);
            if (r.isSuccess()) {
                return chain.filter(exchange);
            }
        }

        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    // 数值越小优先级越高
    @Override
    public int getOrder() {
        return -100;
    }
}
