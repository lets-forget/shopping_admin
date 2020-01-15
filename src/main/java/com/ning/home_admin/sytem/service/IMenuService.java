package com.ning.home_admin.sytem.service;

import com.ning.home_admin.commons.entity.MenuTree;
import com.ning.home_admin.sytem.pojo.Menu;

import java.util.List;

public interface IMenuService {
    List<Menu> findUserPermissions(String userName);

    MenuTree<Menu> findMenus();
}
