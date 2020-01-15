package com.ning.home_admin.sytem.controller;

import com.ning.home_admin.sytem.pojo.Category;
import com.ning.home_admin.sytem.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/list")
    public List<Category> list(){
        String redisKey = "admin_category";
        List<Category> listUser = null;
        //判断是否包含，如果有去redis去拿
        if(redisTemplate.hasKey(redisKey)) {
            return (List<Category>) redisTemplate.opsForValue().get(redisKey);
        } else {
            //如果没有去数据库拿
            listUser = categoryService.findCategory();
            redisTemplate.opsForValue().set(redisKey, listUser);
            return listUser;
        }
    }
}
