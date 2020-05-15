package com.chen.mywebflux;

import com.chen.mywebflux.config.ApplicationContextHolder;
import com.chen.mywebflux.config.WebApiAspectConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyWebFluxApplicationTests {

    @Test
    void contextLoads() {
        WebApiAspectConfig webApiAspectConfig = ApplicationContextHolder.getBean(WebApiAspectConfig.class);
        System.out.println(webApiAspectConfig);
    }

}
