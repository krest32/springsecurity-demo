package com.krest.acl.service;

import com.krest.acl.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.krest.common.Response.R;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author krest
 * @since 2021-05-20
 */
public interface UserService extends IService<User> {

    User selectByUserName(String userName);

    R getAllAdminUser();

    R delete(String id);
}
