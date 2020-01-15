package com.ning.home_admin.sytem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ning.home_admin.commons.config.ShiroRealm;
import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.commons.utils.PageUtil;
import com.ning.home_admin.sytem.mapper.RoleMapper;
import com.ning.home_admin.sytem.mapper.RoleMenuMapper;
import com.ning.home_admin.sytem.mapper.UserRoleMapper;
import com.ning.home_admin.sytem.pojo.Role;
import com.ning.home_admin.sytem.pojo.RoleMenu;
import com.ning.home_admin.sytem.service.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class IRoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ShiroRealm shiroRealm;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public List<Role> findUserRole(String userName) {
        return roleMapper.findUserRole(userName);
    }

    @Override
    public PageUtil findRoles(Page page) {
        PageUtil pageUtil=new PageUtil();
        page.setLimit(10);
        PageHelper.startPage(page.getPage(),page.getLimit());
        List<Role> roleList=roleMapper.selectAll(page);
        PageInfo<Role> pageInfo=new PageInfo<>(roleList);
        pageUtil.setData(pageInfo.getList());
        pageUtil.setCount(pageInfo.getTotal());
        return pageUtil;
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAllRols();
    }

    @Override
    @Transactional
    public void createRole(Role role) {
        role.setCreateTime(new Date());
        this.roleMapper.insert(role);
        this.saveRoleMenus(role);
    }

    @Override
    public void deleteRoleById(String roleIds) {
        List<String> list = Arrays.asList(roleIds.split(","));
        this.roleMapper.delete(list);

        this.roleMenuMapper.deleteRoleMenusByRoleId(list);
        this.userRoleMapper.deleteUserRolesByRoleId(list);
    }

    @Override
    public void updateRole(Role role) {
        role.setUpdateTime(new Date());
        roleMapper.updateById(role);
        List<String> roleIdList = new ArrayList<>();
        roleIdList.add(String.valueOf(role.getRoleId()));
        this.roleMenuMapper.deleteRoleMenusByRoleId(roleIdList);
        saveRoleMenus(role);
        shiroRealm.clearAuthorizationInfoCache();
    }

    private void saveRoleMenus(Role role) {
        if (StringUtils.isNotBlank(role.getMenuIds())) {
            String[] menuIds = role.getMenuIds().split(",");
            List<RoleMenu> roleMenus = new ArrayList<>();
            Arrays.stream(menuIds).forEach(menuId -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(Integer.valueOf(menuId));
                roleMenu.setRoleId(role.getRoleId());
                roleMenus.add(roleMenu);
            });
            roleMenuMapper.insert(roleMenus);
        }
    }
}
