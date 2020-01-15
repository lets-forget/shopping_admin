package com.ning.home_admin.sytem.mapper;

import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.sytem.pojo.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    Role selectByPrimaryKey(Integer roleId);

    List<Role> selectAll(Page page);

    int updateByPrimaryKey(Role record);

    List<Role> findUserRole(String userName);

    List<Role> selectAllRols();

    void delete(List<String> list);

    void updateById(Role role);
}