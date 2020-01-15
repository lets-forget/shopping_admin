package com.ning.home_admin.sytem.controller;

import com.ning.home_admin.commons.annotation.Log;
import com.ning.home_admin.commons.entity.FebsResponse;
import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.commons.exception.FebsException;
import com.ning.home_admin.commons.utils.PageUtil;
import com.ning.home_admin.sytem.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/list")
    public PageUtil orderList(Page page){
        PageUtil pageUtil=orderService.findOrderList(page);
        return pageUtil;
    }

    @Log("删除订单")
    @GetMapping("/delete/{id}")
    public FebsResponse delete(@PathVariable("id") String ids) throws FebsException {
        try {
            this.orderService.delete(ids.split(","));
        }catch (Exception e){
            throw new FebsException("删除失败");
        }
        return new FebsResponse().success();
    }
}
