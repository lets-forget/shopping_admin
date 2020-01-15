package com.ning.home_admin.sytem.mapper;

import com.ning.home_admin.sytem.pojo.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu record);

    Menu selectByPrimaryKey(Integer menuId);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    List<Menu> findUserPermissions(String userName);
}