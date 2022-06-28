package com.krest.acl.controller;

import com.krest.common.Response.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: krest
 * @date: 2021/5/26 00:39
 * @description:
 */
@Api(tags="测试Session功能")
@RequestMapping("/springSession/test")
@RestController
public class SessionController {

    @GetMapping("mockSet/{key}/{value}")
    public R set(@PathVariable String key,
                 @PathVariable String value,
                 HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
        return R.ok().message("成功模拟登录");
    }


    @GetMapping("mockGet/{key}")
    public R get(@PathVariable String key, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String attribute = (String )session.getAttribute(key);
        System.out.println(session.getId());
        return R.ok().data("attribute",attribute);
    }

}
