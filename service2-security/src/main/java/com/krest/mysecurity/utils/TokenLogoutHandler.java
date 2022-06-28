package com.krest.mysecurity.utils;

import com.krest.common.Response.R;
import com.krest.common.utils.JwtUtils;
import com.krest.common.utils.ResponseUtil;
import com.krest.mysecurity.entity.SecurityUser;
import io.jsonwebtoken.Jwt;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: krest
 * @date: 2021/5/19 22:20
 * @description: 推出的处理器
 */
public class TokenLogoutHandler implements LogoutHandler {

    private RedisTemplate redisTemplate;

    public TokenLogoutHandler( RedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    /**
     * 从cookies删除token信息
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication
     */
    @Override
    public void logout(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       Authentication authentication) {

        // 2. token不为空，先移除token，然后从redis中删除token;
        // 但是没有得到token
        //用户登陆成功后，1.生成token，2. 将认证成功的信息放入到redis中

        String token = httpServletRequest.getHeader("Java-Security-Token");

        if(token!=null){
            String userId = JwtUtils.getMemberIdByJwtToken(token);
            redisTemplate.delete("RedisToken"+userId);
            redisTemplate.delete("RedisPermissionValueList"+userId);
        }
        // 完成推出的操作
        ResponseUtil.out(httpServletResponse, R.ok());
    }
}
