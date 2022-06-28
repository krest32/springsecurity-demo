package com.krest.acl.mapper;

import com.krest.acl.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.krest.acl.entity.vo.UserRoleVo;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author krest
 * @since 2021-05-20
 */
public interface UserMapper extends BaseMapper<User> {


    List<UserRoleVo> getAllAdminUser();
}
