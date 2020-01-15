package com.ning.home_admin.sytem.controller;

import com.ning.home_admin.commons.annotation.Log;
import com.ning.home_admin.commons.entity.FebsResponse;
import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.commons.exception.FebsException;
import com.ning.home_admin.commons.utils.PageUtil;
import com.ning.home_admin.sytem.pojo.Item;
import com.ning.home_admin.sytem.service.IItemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private IItemService itemService;


    @RequestMapping("/list")
    public PageUtil list(Page page){
        PageUtil pageUtil=itemService.findItemAll(page);
        return pageUtil;
    }

    //删除
    @Log("删除商品")
    @GetMapping("/delete/{id}")
    @RequiresPermissions("item:delete")
    public FebsResponse delete(@PathVariable("id") String id) throws FebsException {
        try{
           this.itemService.deleteById(id.split(","));
        }catch (Exception e){
            throw new FebsException("删除失败");
        }

        return new FebsResponse().success();
    }

    //修改状态
    @Log("修改商品状态")
    @PostMapping("/updateStatus")
    @RequiresPermissions("item:update")
    public FebsResponse updateStatus(String itemId,boolean flag) throws FebsException {
        try {
            this.itemService.updateStatus(itemId,flag);
        }catch (Exception e){
            throw new FebsException("修改状态失败");
        }
        return new FebsResponse().success();
    }

    //修改多个商品状态
    @Log("修改商品状态")
    @PostMapping("/updateStatsAll/{itemId}/{flag}")
    @RequiresPermissions("item:update")
    public FebsResponse updateStatsAll(@PathVariable("itemId") Integer[] itemId,@PathVariable("flag") boolean flag) throws FebsException {
        try {
            this.itemService.updateStatsAll(itemId,flag);
        }catch (Exception e){
            throw new FebsException("修改多个商品状态失败");
        }
        return new FebsResponse().success();
    }

    //修改商品
   // @Log("修改商品")
    @PostMapping("/update")
    @RequiresPermissions("item:update")
    public FebsResponse update(Item item) throws FebsException {
        try{
            itemService.update(item);
        }catch (Exception e){
            throw new FebsException("修改商品失败");
        }
        return new FebsResponse().success();
    }
}
