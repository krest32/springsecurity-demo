package com.krest.acl.mapper;

import com.krest.acl.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.krest.acl.entity.vo.UserRoleVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author krest
 * @since 2021-05-20
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<UserRoleVo> getAll();
}
