package com.ning.home_admin.sytem.controller;


import com.ning.home_admin.commons.annotation.Log;
import com.ning.home_admin.commons.config.ShiroHelper;
import com.ning.home_admin.commons.controller.BaseController;
import com.ning.home_admin.commons.entity.FebsResponse;
import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.commons.exception.FebsException;
import com.ning.home_admin.commons.utils.PageUtil;
import com.ning.home_admin.sytem.pojo.User;
import com.ning.home_admin.sytem.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author MrBird
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/admin")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ShiroHelper shiroHelper;

    @RequestMapping("/userlist")
    @RequiresPermissions("user:view")
    public PageUtil userList(Page page) {
        PageUtil pageUtil=userService.findUserAll(page);
        return pageUtil;
    }
    @RequestMapping("/adminlist")
    @RequiresPermissions("admin:view")
    public PageUtil adminlist(Page page) {
        PageUtil pageUtil=userService.findAdminAll(page);
        return pageUtil;
    }

    @Log("修改前台用户")
    @PostMapping("/userupdate")
    @RequiresPermissions("user:update")
    public FebsResponse userupdate(User user) throws FebsException {
        try{
            this.userService.updatejd_user(user);
        }catch (Exception e){
            throw new FebsException("修改失败");
        }
        return new FebsResponse().success();
    }
    @Log("删除前台用户")
    @GetMapping("/delete/{id}")
    @RequiresPermissions("user:delete")
    public FebsResponse delete(@PathVariable("id") String id) throws FebsException {
        try {
            userService.userdelete(id.split(","));
        }catch (Exception e){
            throw new FebsException("删除失败");
        }
        return new FebsResponse().success();
    }

    @Log("删除后台用户")
    @GetMapping("/deleteAdmin/{id}")
    @RequiresPermissions("user:delete")
    public FebsResponse deleteAdmin(@PathVariable("id") String id) throws FebsException {
        try {
            userService.admindelete(id.split(","));
        }catch (Exception e){
            throw new FebsException("删除失败");
        }
        return new FebsResponse().success();
    }

    @Log("修改后台用户")
    @PostMapping("/update")
    @RequiresPermissions("admin:update")
    public FebsResponse update(User user) throws FebsException {
        try{
            this.userService.updateUser(user);
        }catch (Exception e){
            throw new FebsException("修改用户失败");
        }
        return new FebsResponse().success();
    }

    @Log("修改用户信息")
    @PostMapping("/updateDetail")
    public FebsResponse updateDetail(User user) throws FebsException {
        try {
            this.userService.updateDetail(user);
        }catch (Exception e){
            throw new FebsException("修改用户信息失败");
        }
        return new FebsResponse().success();
    }
    //开启|禁用
    @Log("(开启|禁用)前台用户")
    @GetMapping("/updateStatus")
    @RequiresPermissions("user:update")
    public FebsResponse updateStatus(Integer userId,boolean flag) throws FebsException {
        try{
            userService.updateStatus(userId,flag);
            return new FebsResponse().success();
        }catch (Exception e){
            throw new FebsException("修改失败");
        }
    }

    @GetMapping("/updateAvatar")
    public FebsResponse updateAvatar( String src) throws FebsException {
        try{
            User user = super.getCurrentUser();
            userService.updateAvatar(src,user);
        }catch (Exception e){
            throw new FebsException("修改头像失败");
        }
        return new FebsResponse().success();
    }
}
