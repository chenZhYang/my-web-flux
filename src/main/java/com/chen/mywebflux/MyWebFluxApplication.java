package com.chen.mywebflux;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chen.mywebflux.model.User;
import com.chen.mywebflux.repojo.RespInfo;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
@EnableWebFlux
public class MyWebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWebFluxApplication.class, args);
    }

    @RequestMapping("/my-web-flux")
    public RespInfo test(String test){
        System.out.println("test = "+test);
        User user = new User();
        user.setAge(12);
        user.setName("陈正阳");
        RespInfo respInfo = new RespInfo();
        respInfo.setCode(1);
        respInfo.setData((JSONObject) JSON.toJSON(user));
        return respInfo;
    }

    @RequestMapping("/server-req")
    public String serverReq(ServerHttpRequest serverRequest){
        System.out.println(serverRequest.getPath());
        System.out.println(JSON.toJSONString(serverRequest));
        serverRequest.getLocalAddress().getHostName();
        return JSON.toJSONString(serverRequest);
    }
}
