package com.krest.mysecurity.filter;

import com.krest.common.Response.R;
import com.krest.common.utils.JwtUtils;
import com.krest.common.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 权限过滤器，这主要负责自定义权限
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    private RedisTemplate redisTemplate;

    public TokenAuthenticationFilter(AuthenticationManager authManager, RedisTemplate redisTemplate) {
        super(authManager);
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("开始执行【doFilterInternal】权限拦截器");

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        if (authentication != null) {
            // 如果权限菜单不为空，那么继续向下执行
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            ResponseUtil.out(res, R.error());
        }
        System.out.println("权限拦截器执行完毕");

        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        String token = request.getHeader("Java-Security-Token");
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(token);

        System.out.println("RedisPermissionValueList"+memberIdByJwtToken);
        // 从redis中获取到角色列表
        List<String> permissionValueList = (List<String>) redisTemplate.opsForValue().get("RedisPermissionValueList"+memberIdByJwtToken);
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        System.out.println("permissionValueList"+permissionValueList.size());
        // 遍历角色列表
        for(String permissionValue : permissionValueList) {
            if(StringUtils.isEmpty(permissionValue)) {
                continue;
            }
            System.out.println("permissionValue列表+++++:"+permissionValue);

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
            // 将菜单加入到 菜单列表中
            authorities.add(authority);
        }

        if (!StringUtils.isEmpty(memberIdByJwtToken)) {
            // 如果用户id为空，返回 gust用户权限
            return new UsernamePasswordAuthenticationToken("guest", memberIdByJwtToken, authorities);
        }

        return null;
    }
}