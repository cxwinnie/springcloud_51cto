package com.example.gateway.httpClient;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.common.R;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Component
public class AuthServiceByHttp {

    public R byHttpGet(@RequestParam Map<String, Object> params)
    {
        String html = HttpRequestUtil.getData("http://localhost:8081/auth/hasPermission?token=" + params.get("token").toString());
        return JSON.parseObject(html, R.class);
    }

    public R byHttpPost(@RequestBody Map<String, Object> params)
    {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("token", params.get("token").toString());
        String html = HttpRequestUtil.postData("http://localhost:8081/auth/hasPermission", jsonObj);
        return JSON.parseObject(html, R.class);
    }
}