package com.ning.home_admin.sytem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.commons.utils.PageUtil;
import com.ning.home_admin.sytem.mapper.ItemMapper;
import com.ning.home_admin.sytem.pojo.Item;
import com.ning.home_admin.sytem.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class IItemServiceImpl implements IItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public PageUtil findItemAll(Page page) {
        PageUtil pageUtil=new PageUtil();

        PageHelper.startPage(page.getPage(),page.getLimit());

        List<Item> items = itemMapper.selectAll(page);

        PageInfo<Item> pageInfo=new PageInfo<>(items);
        pageUtil.setData(pageInfo.getList());
        pageUtil.setCount(pageInfo.getTotal());
        return pageUtil;
    }

    @Override
    public void deleteById(String[] split) {
        itemMapper.deleteByPrimaryKey(split);
    }

    @Override
    public Item findById(Integer id) {
        return itemMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateStatus(String itemId, boolean flag) {
        itemMapper.updateStatus(itemId,flag);
    }

    @Override
    public void updateStatsAll(Integer[] itemId, boolean flag) {
        itemMapper.updateStatsAll(itemId,flag);
    }

    @Override
    public void update(Item item) {
        itemMapper.updateByPrimaryKey(item);
    }
}
