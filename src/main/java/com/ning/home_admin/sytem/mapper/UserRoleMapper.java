package com.ning.home_admin.sytem.mapper;

import com.ning.home_admin.sytem.pojo.UserRole;

import java.util.List;

public interface UserRoleMapper {
    int insert(List<UserRole> record);

    List<UserRole> selectAll();

    void delete(Integer userId);

    void deleteUserRolesByRoleId(List<String> list);
}