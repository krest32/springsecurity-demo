package com.krest.employee.controller;


import com.krest.common.Response.R;
import com.krest.common.entityvo.LoginVo;
import com.krest.employee.entity.Tbl;
import com.krest.employee.service.TblService;
import com.netflix.client.http.HttpRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author krest
 * @since 2021-05-20
 */

@Api(tags = "雇员管理接口")
@RestController
@RequestMapping("/employee/tbl")
public class TblController {

    @Autowired
    private TblService tblService;

    @ApiOperation("查询所有雇员")
    @GetMapping("listEmployee")
    public R listEmployee(HttpSession session, HttpServletRequest request){
        List<Tbl> list = tblService.list(null);
//        LoginVo loginUser =(LoginVo) session.getAttribute("loginUser");
//        System.out.println(loginUser.getUsername());
        return R.ok().data("list",list);
    }

    @ApiOperation("根据id删除雇员")
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable String id ){
       tblService.removeById(id);
       return R.ok();
    }

    @ApiOperation("根据id查询雇员")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id ){
        Tbl byId = tblService.getById(id);
        return R.ok().data("employ",byId);
    }

    @ApiOperation("添加")
    @PostMapping("add")
    public R add(@RequestBody Tbl tbl ){
        tblService.save(tbl);
        return R.ok();
    }

    @ApiOperation("更新")
    @PostMapping("update")
    public R update(@RequestBody Tbl tbl ){
        tblService.updateById(tbl);
        return R.ok();
    }
}

