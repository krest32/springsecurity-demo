package com.krest.acl.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: krest
 * @date: 2021/5/20 17:32
 * @description:
 */
@Configuration
public class MybatisConfig {
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
}
