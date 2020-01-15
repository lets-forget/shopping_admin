package com.ning.home_admin.sytem.mapper;

import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.sytem.pojo.Item;

import java.util.List;

public interface ItemMapper {
    int deleteByPrimaryKey(String[] id);

    int insert(Item record);

    Item selectByPrimaryKey(Integer id);

    List<Item> selectAll(Page page);

    int updateByPrimaryKey(Item record);

    void updateStatus(String itemId, boolean flag);

    void updateStatsAll(Integer[] itemId, boolean flag);

}