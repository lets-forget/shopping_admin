package com.ning.home_admin.monitor.controller;

import com.ning.home_admin.commons.controller.BaseController;
import com.ning.home_admin.commons.entity.FebsResponse;
import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.commons.exception.FebsException;
import com.ning.home_admin.commons.utils.PageUtil;
import com.ning.home_admin.monitor.service.ILogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author NKW
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController extends BaseController {

    @Autowired
    private ILogService logService;

    @RequestMapping("/list")
    @RequiresPermissions("log:view")
    public PageUtil list(Page page){
        PageUtil pageUtil=logService.findLogs(page);
        return pageUtil;
    }

    @GetMapping("/del/{id}")
    @RequiresPermissions("log:delete")
    public FebsResponse del(@PathVariable("id") Integer id) throws FebsException {
        try{
            logService.deleteById(id);
            return new FebsResponse().success();
        }catch (Exception e){
            throw new FebsException("删除系统日志失败");
        }
    }

    @GetMapping("/delAll/{ids}")
    @RequiresPermissions("log:delete")
    public FebsResponse delAll(@PathVariable("ids") String ids) throws FebsException {
        try{
            String[] logIds = ids.split(",");
            logService.deleteAllByIds(logIds);
            return new FebsResponse().success();
        }catch (Exception e){
            throw new FebsException("删除系统日志失败");
        }
    }
}
