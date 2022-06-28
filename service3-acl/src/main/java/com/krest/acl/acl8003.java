package com.krest.acl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * @author: krest
 * @date: 2021/5/20 13:16
 * @description:
 */
@EnableRedisHttpSession  // 开启redisSession存储功能
@EnableDiscoveryClient
@MapperScan("com.krest.acl.mapper")
@ComponentScan(basePackages = {"com.krest"})
@SpringBootApplication
public class acl8003 {
    public static void main(String[] args) {
        SpringApplication.run(acl8003.class,args);
    }
}
