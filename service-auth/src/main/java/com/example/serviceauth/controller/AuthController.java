package com.example.serviceauth.controller;

import com.example.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/hasPermission")
    public R hasPermission(@RequestParam Map<String, Object> params) {
        Object token = params.get("token");
        if (token != null && token.toString().length() > 5) {
            return R.ok("验证成功,port:"+port);
        }
        return R.error("验证失败,port:"+port);
    }

    @PostMapping("/hasPermission")
    public R hasPermissionByPost(@RequestBody Map<String, Object> params) throws InterruptedException {
        Object token = params.get("token");
        if (token != null && token.toString().length() > 5) {
            return R.ok("验证成功,port:"+port);
        }
        // throw new RuntimeException("123"); 也会触发熔断器
        Thread.sleep(2000);
        return R.error("验证失败,port:"+port);
    }

}
