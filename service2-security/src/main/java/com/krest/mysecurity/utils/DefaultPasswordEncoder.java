package com.krest.mysecurity.utils;

import com.krest.common.utils.MD5;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author: krest
 * @date: 2021/5/20 19:58
 * @description: 对用户名和密码进行校验
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return MD5.encrypt(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String password) {
        //todo charSequence 是登陆用户输入的密码明文
        System.out.println("执行密码认证");
        System.out.println("charSequence:"+MD5.encrypt((String) charSequence));
        System.out.println("password:"+password);
        return MD5.encrypt((String) charSequence).equals(password);
    }

}
