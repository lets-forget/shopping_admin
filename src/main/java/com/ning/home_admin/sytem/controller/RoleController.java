package com.ning.home_admin.sytem.controller;

import com.ning.home_admin.commons.annotation.Log;
import com.ning.home_admin.commons.entity.FebsResponse;
import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.commons.exception.FebsException;
import com.ning.home_admin.commons.utils.PageUtil;
import com.ning.home_admin.sytem.pojo.Role;
import com.ning.home_admin.sytem.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/list")
    @RequiresPermissions("role:view")
    public FebsResponse getAllRoles() {
        return new FebsResponse().success().data(roleService.selectAll());
    }

    @RequestMapping("/tablelist")
    @RequiresPermissions("role:view")
    public PageUtil getAllRoles(Page page) {
        PageUtil pageUtil=roleService.findRoles(page);
        return pageUtil;
    }

    //新增
    @Log("新增角色")
    @PostMapping("/add")
    @RequiresPermissions("role:add")
    public FebsResponse addRole(Role role) throws FebsException {
        try {
            this.roleService.createRole(role);
        }catch (Exception e){
            throw new FebsException("添加角色失败");
        }
        return new FebsResponse().success();
    }

    //修改
    @Log("修改角色")
    @PostMapping("/update")
    @RequiresPermissions("role:update")
    public FebsResponse updateRole(Role role) throws FebsException {
        try {
            this.roleService.updateRole(role);
        }catch (Exception e){
            throw new FebsException("修改角色失败");
        }
        return new FebsResponse().success();
    }
    //删除
    @Log("删除角色")
    @GetMapping("/delete/{roleIds}")
    @RequiresPermissions("role:delete")
    public FebsResponse deleteRole(@PathVariable("roleIds") String roleIds) throws FebsException {
        try {
            this.roleService.deleteRoleById(roleIds);
        }catch (Exception e){
            throw new FebsException("删除角色失败");
        }
        return new FebsResponse().success();
    }

}
