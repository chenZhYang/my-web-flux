package com.chen.mywebflux;

import com.chen.mywebflux.config.ApplicationContextHolder;
import com.chen.mywebflux.config.WebApiAspectConfig;
import com.chen.mywebflux.db.entity.SevenStarHistory;
import com.chen.mywebflux.db.mapper.SevenStarHistoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MyWebFluxApplicationTests {

    @Autowired
    SevenStarHistoryMapper sevenStarHistoryMapper;

    @Test
    void contextLoads() {
        WebApiAspectConfig webApiAspectConfig = ApplicationContextHolder.getBean(WebApiAspectConfig.class);
        System.out.println(webApiAspectConfig);
    }

    @Test
    void test(){
        List<SevenStarHistory> list = sevenStarHistoryMapper.selectByTime("2019-01-01");
        System.out.println(list);
    }

}
