package com.krest.acl.controller;


import com.krest.acl.entity.UserRole;
import com.krest.acl.entity.vo.UserRoleVo;
import com.krest.acl.service.UserRoleService;
import com.krest.common.Response.R;
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
@RequestMapping("/acl/user-role")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("getAll")
    public R getAll(){
        List<UserRoleVo> userRoleVo = userRoleService.getAll();
        return R.ok().data("list",userRoleVo);
    }

    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable String id){
        return  userRoleService.delete(id);
    }



}

