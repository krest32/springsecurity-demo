package com.krest.acl.service;

import com.krest.acl.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.krest.acl.entity.vo.UserRoleVo;
import com.krest.common.Response.R;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author krest
 * @since 2021-05-20
 */
public interface UserRoleService extends IService<UserRole> {

    List<UserRoleVo> getAll();

    R delete(String id);
}
