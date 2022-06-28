package com.krest.mysecurity.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krest.common.Response.R;
import com.krest.common.utils.JwtUtils;
import com.krest.common.utils.ResponseUtil;
import com.krest.mysecurity.entity.LoginUser;
import com.krest.mysecurity.entity.SecurityUser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author: krest
 * @date: 2021/5/19 22:33
 * @description: 登陆（认证）过滤器
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    /**
     *     针对权限管理的工具类，框架进行提供
     */

    private AuthenticationManager authenticationManager;
    private RedisTemplate redisTemplate;

    /**
     *  构造方法，可以直接调用
     */
    public TokenLoginFilter(AuthenticationManager authenticationManager,
                            RedisTemplate redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.redisTemplate = redisTemplate;
        // 仅仅Post提交方式为false，设置其他方式也可以进行提交
        this.setPostOnly(false);
        // 配置需要提交登陆认证的地址
        this.setRequiresAuthenticationRequestMatcher(
                new AntPathRequestMatcher("/acl/user/login","POST"));
    }


    /**
     * 用户输入信息后，得到登陆的用户信息，然后返回该用户现在所拥有的权限信息
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {
        try {
            // 从请求中得到用户信息，然后赋值给到User.class
            LoginUser user = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
            System.out.println("执行尝试登陆的过滤器：【用户输入的user信息】"+ user);
            // 返回一个用户的信息和权限的信息
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>());

            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            return authenticate;


        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    /**
     * 认证成功后调用的方法，会获取认证之后的用户信息
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        System.out.println("登陆成功进入successfulAuthentication的过滤器");

        SecurityUser securityUser = (SecurityUser) authResult.getPrincipal();

        //用户登陆成功后，1.生成token，2. 将认证成功的信息放入到redis中
        String token = JwtUtils.getJwtToken(securityUser.getCurrentUserInfo().getId());

        // 向redis中存放token
        redisTemplate.opsForValue().set(
                "RedisPermissionValueList"+securityUser.getCurrentUserInfo().getId(),
                securityUser.getPermissionValueList());

        // 向 Response 中返回token
        System.out.println("登陆成功后生成token，放入搭配response中："+token);
        redisTemplate.opsForValue().set("RedisToken"+securityUser.getCurrentUserInfo().getId(),token);

        ResponseUtil.out(response, R.ok().data("token", token));
    }

    /**
     * 认证失败后调用的方法，返回一个错误的信息即可
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response, R.error().message("登陆认证失败"));
    }
}
