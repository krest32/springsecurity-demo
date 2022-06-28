package com.krest.employee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * @author: krest
 * @date: 2021/5/20 13:16
 * @description:
 */
@EnableRedisHttpSession
@EnableScheduling
@EnableDiscoveryClient
@MapperScan("com.krest.employee.mapper")
@ComponentScan(basePackages = {"com.krest"})
@SpringBootApplication
public class main8004 {
    public static void main(String[] args) {
        SpringApplication.run(main8004.class,args);
    }
}
