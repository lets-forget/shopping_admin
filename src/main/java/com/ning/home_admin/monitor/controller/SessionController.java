package com.ning.home_admin.monitor.controller;


import com.ning.home_admin.commons.entity.FebsResponse;
import com.ning.home_admin.monitor.entity.ActiveUser;
import com.ning.home_admin.monitor.service.ISessionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NKW
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private ISessionService sessionService;

    @RequestMapping("/list")
    @RequiresPermissions("online:view")
    public Map<String, Object> list(String username) {
        List<ActiveUser> list = sessionService.list(username);
        Map<String, Object> data = new HashMap<>();
        data.put("data", list);
        data.put("code", 0);
        return data;
    }

    @GetMapping("/delete/{id}")
    @RequiresPermissions("online:kick")
    public FebsResponse forceLogout(@PathVariable String id) {
        sessionService.forceLogout(id);
        return new FebsResponse().success();
    }
}
