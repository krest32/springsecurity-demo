package com.krest.acl.service.impl;

import com.krest.acl.entity.Role;
import com.krest.acl.mapper.RoleMapper;
import com.krest.acl.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author krest
 * @since 2021-05-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
