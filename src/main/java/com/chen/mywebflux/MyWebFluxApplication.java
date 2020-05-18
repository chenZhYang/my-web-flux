package com.chen.mywebflux;

import com.chen.mywebflux.config.ApplicationContextHolder;
import com.chen.mywebflux.web.SevenStarService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@RestController
@EnableWebFlux
public class MyWebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWebFluxApplication.class, args);
    }


    @GetMapping(value = "/statisticsDrawNum")
    public String statisticsDrawNum(String drawTime,Integer type,Integer size){
        return ApplicationContextHolder.getBean(SevenStarService.class).statisticsDrawNum(drawTime,type,size);
    }


    @GetMapping(value = "/unopened")
    public String unopened(){
        return ApplicationContextHolder.getBean(SevenStarService.class).statisticsUnopened();
    }
}
