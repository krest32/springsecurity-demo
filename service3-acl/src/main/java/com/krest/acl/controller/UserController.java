package com.krest.acl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.krest.acl.entity.Role;
import com.krest.acl.entity.User;
import com.krest.acl.entity.UserRole;
import com.krest.acl.service.RoleService;
import com.krest.acl.service.UserRoleService;
import com.krest.common.entityvo.LoginVo;
import com.krest.acl.entity.vo.UserInfoVo;
import com.krest.acl.service.UserService;
import com.krest.common.Response.R;
import com.krest.common.utils.JwtUtils;
import com.krest.common.utils.MD5;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author krest
 * @since 2021-05-20
 */
@RestController
@RequestMapping("/acl/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation("保存新用户")
    @PostMapping("saveAdminUser")
    public R saveAdminUser(@RequestBody User user){
        UserRole userRole = new UserRole();
        userRole.setRoleId(user.getRoleId());
        userRole.setUserId(user.getId());
        userRoleService.save(userRole);
        // 更新user信息
        userService.updateById(user);
        return R.ok();
    }


    @ApiOperation("根据id更新用户")
    @PostMapping("updateAdminUserById")
    public R updateAdminUserById(@RequestBody User user){
        UserRole userRole = new UserRole();
        userRole.setRoleId(user.getRoleId());
        userRole.setUserId(user.getId());
        //更新role信息
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());
        UserRole one = userRoleService.getOne(queryWrapper);
        if(one!=null){
            userRoleService.update(userRole,queryWrapper);
        }else{
            userRoleService.save(userRole);
        }
        // 更新user信息
        userService.updateById(user);
        return R.ok();
    }



    @ApiOperation("根据id得到用户")
    @PostMapping("getAdminUserById/{id}")
    public R getAdminUserById(@PathVariable String id){
        User user = userService.getById(id);

        //获取role信息
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());
        UserRole one = userRoleService.getOne(queryWrapper);

        if(one !=null && !one.getRoleId().isEmpty()){
            user.setRoleId(one.getRoleId());
        }

        return R.ok().data("data",user);
    }

    @ApiOperation("0.获取所有用户")
    @PostMapping("getAllAdminUser")
    public R getAllAdminUser(){
        return userService.getAllAdminUser();
    }


    @ApiOperation("删除用户")
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable String id){
        return userService.delete(id);
    }

    @ApiOperation("1.登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo,
                   HttpServletResponse response,
                   HttpSession session){
        if (checkLogInfo(loginVo)) {
            return R.error().message("登录信息错误");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",loginVo.getUsername());
        User user = userService.getOne(queryWrapper);
        if(!user.getPassword().equals(MD5.encrypt(loginVo.getPassword()))){
            return R.error().message("密码错误");
        }

        // 登陆成功之后，可以添加一个cookies
        session.setAttribute("loginUser",loginVo);
        Cookie cookie = new Cookie("testSecurity","123");
        response.addCookie(cookie);

        String jwtToken = JwtUtils.getJwtToken(user.getId());
        return R.ok().data("token",jwtToken);
    }

    @ApiOperation("2.根据token返回userInfo")
    @GetMapping("info")
    public R login(@RequestParam("token") String token){
        System.out.println(token);
        String id = JwtUtils.getMemberIdByJwtToken(token);

        User user = userService.getById(id);
        UserInfoVo admin = new UserInfoVo();
        ArrayList<String> list = new ArrayList<>();
        list.add("小白");
        admin.setRoles(list);
        admin.setUsername(user.getUsername());
        System.out.println("系统用户信息【admin】："+admin);

        return R.ok().data("roles",admin.getRoles()).data("name",admin.getUsername()).data("avatar",admin.getAvatar());
    }

    @GetMapping("getIdBytoken/{token}")
    public  R getId(@PathVariable String token){
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(token);
        return R.ok().data("id",memberIdByJwtToken);
    }

    @ApiOperation("3.注册接口")
    @PostMapping("register")
    public R register(@RequestBody LoginVo loginVo){
        System.out.println(loginVo);
        if (checkLogInfo(loginVo)) {
            return R.error().message("注册信息有误");
        }

        // 检测用户名信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",loginVo.getUsername());
        int count = userService.count(queryWrapper);
        if(count>0){
            return R.error().message("该用户名已经存在");
        }

        // 添加访客权限
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        queryWrapper.eq("游客","游客");


        User user = new User();
        user.setUsername(loginVo.getUsername());
        user.setPassword(MD5.encrypt(loginVo.getPassword()));
        userService.save(user);

        return R.ok().message("注册成功");
    }

    @ApiOperation("4.登出接口")
    @PostMapping("logout")
    public R logout(HttpServletRequest request){
        String token = request.getParameter("token");
        System.out.println("接口token："+token);
        return R.ok();
    }

    private boolean checkLogInfo(@RequestBody LoginVo loginVo) {
        if(loginVo.getUsername().isEmpty() ||
                loginVo.getPassword().isEmpty() ||
                loginVo.getPassword().length()<6){
            return true;
        }
        return false;
    }

}

