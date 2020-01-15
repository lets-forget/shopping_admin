package com.ning.home_admin.sytem.mapper;

import com.ning.home_admin.sytem.pojo.RoleMenu;

import java.util.List;

public interface RoleMenuMapper {
    int insert(List<RoleMenu> record);

    List<RoleMenu> selectAll();

    void deleteRoleMenusByRoleId(List<String> list);

}