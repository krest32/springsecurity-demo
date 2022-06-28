package com.krest.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * 启动使用网关，并且注册到 NACOS 中，可以当
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class GateWayBoot8005 {
    public static void main(String[] args) {
        SpringApplication.run(GateWayBoot8005.class, args);
    }
}
