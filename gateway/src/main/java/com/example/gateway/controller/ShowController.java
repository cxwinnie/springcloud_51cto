package com.example.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ShowController {

    @Value("${custom}")
    String path;

    @GetMapping("/showConfig")
    public String showConfig() {
        return "获取到文件存放的路径是：" + path;
    }

}
