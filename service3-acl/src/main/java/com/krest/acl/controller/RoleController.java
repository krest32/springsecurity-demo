package com.krest.acl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.krest.acl.entity.Role;
import com.krest.acl.entity.UserRole;
import com.krest.acl.service.RoleService;
import com.krest.acl.service.UserRoleService;
import com.krest.common.Response.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author krest
 * @since 2021-05-20
 */
@RestController
@RequestMapping("/acl/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;



    @ApiOperation("根据id更新Role信息")
    @PostMapping("updateRoleById")
    public R updateRoleById(@RequestBody Role role){
        roleService.updateById(role);
        return R.ok();
    }

    @ApiOperation("获取所有的Role")
    @GetMapping("getRoleById/{id}")
    public R getRoleById(@PathVariable String id){
        Role role = roleService.getById(id);

        return R.ok().data("role",role);
    }

    @ApiOperation("获取所有的Role")
    @GetMapping("getAll")
    public R getAll(){
        List<Role> list = roleService.list(null);
        return R.ok().data("list",list);
    }

    @ApiOperation("根据id删除Role信息")
    @DeleteMapping("deleteRoleById/{id}")
    public R deleteRoleById(@PathVariable String id){
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",id);
        int count = userRoleService.count(queryWrapper);
        if(count>0){
            return R.error().message("该role正在被使用");
        }else {
            roleService.removeById(id);
            return R.ok();
        }

    }

    @ApiOperation("添加新的角色")
    @PostMapping("addNewRole")
    public R addNewRole(@RequestBody Role role){
        roleService.save(role);
        return R.ok();
    }




}

