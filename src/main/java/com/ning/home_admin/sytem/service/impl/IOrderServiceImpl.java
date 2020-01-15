package com.ning.home_admin.sytem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.commons.utils.PageUtil;
import com.ning.home_admin.sytem.mapper.OrderMapper;
import com.ning.home_admin.sytem.pojo.Order;
import com.ning.home_admin.sytem.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class IOrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageUtil findOrderList(Page page) {
        PageUtil pageUtil=new PageUtil();

        PageHelper.startPage(page.getPage(),page.getLimit());
        List<Order> orders = orderMapper.selectAll(page);
        PageInfo<Order> pageInfo=new PageInfo<>(orders);
        pageUtil.setData(pageInfo.getList());
        pageUtil.setCount(pageInfo.getTotal());
        return pageUtil;
    }

    @Override
    public void delete(String[] split) {
        this.orderMapper.deleteByPrimaryKey(split);
    }
}
