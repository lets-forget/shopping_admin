package com.ning.home_admin.sytem.service;

import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.commons.utils.PageUtil;

public interface IOrderService {
    PageUtil findOrderList(Page page);

    void delete(String[] split);
}
