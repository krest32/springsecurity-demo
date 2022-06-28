package com.krest.common.session;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @author: krest
 * @date: 2021/5/25 14:48
 * @description: Session存储的信息
 */

@Configuration
public class SessionRedisConfig {
    /**
     * 用来设置对应的domain和cookies
     * 目前我自己的电脑没有生效这个域名的cookies
     */
    @Bean
    public CookieSerializer cookieSerializer(){
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
//        cookieSerializer.setDomainName("krest.com");
        cookieSerializer.setCookieName("security-session");

        return cookieSerializer;
    }

    @Bean(name = "springSessionDefaultRedisSerializer")
    public RedisSerializer springSessionDefaultRedisSerializer() {
        return RedisSerializer.json();
    }
}
