package com.ning.home_admin.sytem.controller;

import com.ning.home_admin.commons.config.ShiroHelper;
import com.ning.home_admin.commons.controller.BaseController;
import com.ning.home_admin.commons.utils.DateUtil;
import com.ning.home_admin.sytem.pojo.Role;
import com.ning.home_admin.sytem.pojo.User;
import com.ning.home_admin.sytem.service.IRoleService;
import com.ning.home_admin.sytem.service.IUserService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UrlController extends BaseController {


    @Autowired
    private ShiroHelper shiroHelper;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;


    @GetMapping(value  = {"/","/index"})
    public String index(Model model) {
        User user = super.getCurrentUser();
        User currentUserDetail = userService.findByName(user.getUsername());
        model.addAttribute("user", currentUserDetail);
        return "index";
    }
    //管理员显示
    @GetMapping("/admin/detail/{username}")
    public String systemAdminDetail(@PathVariable String username, Model model) {
        resolveAdminModel(username, model, true);
        return "admin/adminDetail";
    }
    //用户显示
    @GetMapping("/user/detail/{username}")
    public String systemUserDetail(@PathVariable String username, Model model) {
        resolveUserModel(username, model, true);
        return "user/userDetail";
    }
    //管理员修改
    @GetMapping("/admin/update/{username}")
    @RequiresPermissions("admin:update")
    public String systemAdminUpdate(@PathVariable String username, Model model) {
        resolveAdminModel(username, model, true);
        return "admin/adminUpdate";
    }
    //用户修改
    @GetMapping("/user/update/{username}")
    @RequiresPermissions("user:update")
    public String systemUserUpdate(@PathVariable String username, Model model) {
        resolveUserModel(username, model, true);
        return "user/userUpdate";
    }

    @PostMapping("/permission/list")
    @ResponseBody
    @RequiresPermissions("admin:update")
    public List<Role> permissionList(String username){
       //通过用户名查询角色
        return this.roleService.findUserRole(username);
    }
    @RequestMapping("/welcome")
    public String welcome(Model model){
        User user = super.getCurrentUser();
        model.addAttribute("user", userService.findByName(user.getUsername()));
        return "welcome";
    }

    @RequestMapping("/member-list")
    public String member_list(){
        return "member-list";
    }
    //用户信息
    @GetMapping("/userProfile")
    public String userProfile(Model model){
        User user = super.getCurrentUser();
        User byName = userService.findByName(user.getUsername());
        model.addAttribute("user",byName);
        return "user/userProfile";
    }
    @GetMapping("/order")
    public String order(){
        return "order";
    }
    @GetMapping("/item")
    public String item(){
        return "item";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
    @GetMapping("/role")
    @RequiresPermissions("role:view")
    public String role(){
        return "role/role";
    }
    @RequestMapping("/member-del")
    public String member_del(){
        return "member-del";
    }
    @RequestMapping("/login-log")
    public String longin_log(){
        return "login_log";
    }
    @RequestMapping("/system-log")
    public String system_log(){
        return "system_log";
    }

    @GetMapping("/page/tpl/tpl-theme.html")
    public String theme(){
        return "tpl/tpl-theme";
    }
    @GetMapping("/page/tpl/tpl-note.html")
    public String note(){
        return "note";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/error/403")
    public String error403(){
        return "error/403";
    }
    @RequestMapping("/test")
    public String test(){
        return "Test";
    }
    @RequestMapping("/online")
    public String online(){
        return "online";
    }
    @RequestMapping("/user")
    public String user(){
        return "user";
    }
    @GetMapping("/avatar")
    public String avatar(){
        return "user/avatar";
    }
    private void resolveUserModel(String username, Model model, Boolean transform) {
        User user = userService.selectByName(username);
        model.addAttribute("user", user);
        if (null != user.getLastTime())
            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
    }
    private void resolveAdminModel(String username, Model model, Boolean transform) {
        User user = userService.findByName(username);
        model.addAttribute("user", user);
        if (null != user.getLastTime())
            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
    }
}
