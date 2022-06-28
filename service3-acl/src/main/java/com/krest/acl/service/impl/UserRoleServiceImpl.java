package com.krest.acl.service.impl;

import com.krest.acl.entity.UserRole;
import com.krest.acl.entity.vo.UserRoleVo;
import com.krest.acl.mapper.UserRoleMapper;
import com.krest.acl.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.krest.acl.service.UserService;
import com.krest.common.Response.R;
import com.krest.common.exception.myException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author krest
 * @since 2021-05-20
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    private UserService userService;

    @Override
    public List<UserRoleVo> getAll() {
        List<UserRoleVo> list = baseMapper.getAll();
        return list;
    }

    @Override
    public R delete(String id) {
        UserRole userRole = baseMapper.selectById(id);
        try{
            String userId = userRole.getUserId();
            userService.removeById(userId);
            baseMapper.deleteById(id);
        }catch (Exception e){
            throw new myException(500,"删除系统用户错误");
        }
        return R.ok().message("删除用户成功");
    }
}
