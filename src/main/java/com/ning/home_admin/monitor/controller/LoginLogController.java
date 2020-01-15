package com.ning.home_admin.monitor.controller;

import com.ning.home_admin.commons.annotation.Log;
import com.ning.home_admin.commons.entity.FebsResponse;
import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.commons.exception.FebsException;
import com.ning.home_admin.commons.utils.PageUtil;
import com.ning.home_admin.monitor.service.ILoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/loginLog")
public class LoginLogController {

    @Autowired
    private ILoginLogService loginLogService;


//分页显示数据
    @RequestMapping("/list")
    @RequiresPermissions("login_log:view")
    public PageUtil list(Page page){
        PageUtil pageUtil=loginLogService.findLoginLogs(page);
        return pageUtil;
    }

    @Log("删除登录日志")
    @GetMapping("/del/{id}")
    @RequiresPermissions("login_log:delete")
    public FebsResponse del(@PathVariable("id") Integer id) throws FebsException {
        try {
            loginLogService.deleteById(id);
            return new FebsResponse().success();

        } catch (Exception e) {
            String message = "删除日志失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除多个登录日志")
    @GetMapping("/delAll/{ids}")
    @RequiresPermissions("login_log:delete")
    public FebsResponse delAll(@PathVariable("ids") String ids) throws FebsException {
       try{
           String[] loginLogIds = ids.split(",");
           loginLogService.deleteAllByIds(loginLogIds);
           return new FebsResponse().success();
       }catch (Exception e){
           throw new FebsException("删除日志失败");
       }
    }
}
