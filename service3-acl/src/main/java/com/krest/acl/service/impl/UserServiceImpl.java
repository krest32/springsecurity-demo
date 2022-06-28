package com.krest.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.krest.acl.entity.Role;
import com.krest.acl.entity.User;
import com.krest.acl.entity.UserRole;
import com.krest.acl.entity.vo.UserRoleVo;
import com.krest.acl.mapper.UserMapper;
import com.krest.acl.service.RoleService;
import com.krest.acl.service.UserRoleService;
import com.krest.acl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.krest.common.Response.R;
import com.krest.common.exception.myException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author krest
 * @since 2021-05-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;


    @Override
    public User selectByUserName(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userName);
        List<User> users = new ArrayList<>();
        try {
            users = baseMapper.selectList(queryWrapper);
            if(users.size()!=1){
                throw new myException(503,"未找到登录用户");
            }
        }catch (Exception e){
            throw new myException(503,"未找到登录用户");
        }

        return users.get(0);
    }

    @Override
    public R getAllAdminUser() {
        List<Role> roleList = roleService.list(null);
        List<UserRole> userRoleList = userRoleService.list(null);
        List<User> userList = baseMapper.selectList(null);
        List<UserRoleVo> collect = userList.stream().map((user)->{
            return editUSerInfo(user,roleList,userRoleList);
        }).collect(Collectors.toList());
        return R.ok().data("list",collect);
    }

    @Override
    public R delete(String id) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        userRoleService.remove(queryWrapper);
        baseMapper.deleteById(id);
        return R.ok();
    }

    private UserRoleVo editUSerInfo(User user, List<Role> roleList, List<UserRole> userRoleList) {
        UserRoleVo userRoleVo = new UserRoleVo();
        userRoleVo.setUserId(user.getId());
        userRoleVo.setUserName(user.getUsername());
        for (UserRole userRole : userRoleList) {
            if(userRole.getUserId().equals(user.getId())){
                userRoleVo.setId(userRole.getId());
                userRoleVo.setUserId(user.getId());
                userRoleVo.setUserName(user.getUsername());
                userRoleVo.setRoleId(userRole.getRoleId());
                for (Role role : roleList) {
                    if(userRole.getRoleId().equals(role.getId())){
                        userRoleVo.setRoleName(role.getRoleName());
                        break;
                    }
                }
                break;
            }
        }
        return userRoleVo;
    }
}
