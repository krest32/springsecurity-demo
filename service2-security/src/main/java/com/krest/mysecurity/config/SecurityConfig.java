package com.krest.mysecurity.config;

import com.krest.common.utils.JwtUtils;
import com.krest.mysecurity.filter.TokenAuthenticationFilter;
import com.krest.mysecurity.filter.TokenLoginFilter;
import com.krest.mysecurity.utils.DefaultPasswordEncoder;
import com.krest.mysecurity.utils.TokenLogoutHandler;
import com.krest.mysecurity.utils.UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author: krest
 * @date: 2021/5/20 19:41
 * @description:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 用来操作用户权限信息的工具
     */
    private UserDetailsService userDetailsService;
    private DefaultPasswordEncoder defaultPasswordEncoder;
    private RedisTemplate redisTemplate;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService,
                          DefaultPasswordEncoder defaultPasswordEncoder,
                          RedisTemplate redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.redisTemplate = redisTemplate;
    }


    /**
     * 配置核心的内容
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("执行Spring Security 配置文件");
        //http 异常处理的方法
        http.exceptionHandling()
                // 对于没有权限的路径抛出异常
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                //关闭这个请求，防止页面无法登陆，主要是针对跨域请求的
                .and().csrf().disable()
                .authorizeRequests().anyRequest().permitAll()
                .and().logout().logoutUrl("/acl/user/logout")
                .addLogoutHandler(new TokenLogoutHandler(redisTemplate)).and()
                .addFilter(new TokenLoginFilter(authenticationManager(), redisTemplate))
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), redisTemplate))

                .httpBasic();
        ;

    }

    /**
     * 密码处理，默认密码必须加密才能够使用
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //表示从哪里得到用户信息，同时设置用户的加密方式
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }
    /**
     * 配置哪些请求不拦截
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**",
                "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"
        );
    }


}