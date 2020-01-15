package com.ning.home_admin.sytem.service;

import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.commons.utils.PageUtil;
import com.ning.home_admin.sytem.pojo.Item;

public interface IItemService {
    PageUtil findItemAll(Page page);

    void deleteById(String[] split);

    Item findById(Integer id);

    void updateStatus(String itemId, boolean flag);

    void updateStatsAll(Integer[] itemId, boolean flag);

    void update(Item item);
}
