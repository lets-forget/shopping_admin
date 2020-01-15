package com.ning.home_admin.sytem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ning.home_admin.commons.config.ShiroRealm;
import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.commons.utils.FebsUtil;
import com.ning.home_admin.commons.utils.PageUtil;
import com.ning.home_admin.sytem.mapper.UserMapper;
import com.ning.home_admin.sytem.mapper.UserRoleMapper;
import com.ning.home_admin.sytem.pojo.User;
import com.ning.home_admin.sytem.pojo.UserRole;
import com.ning.home_admin.sytem.service.IUserService;
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
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private ShiroRealm shiroRealm;

    @Override
    public User findByName(String username) {
        return userMapper.findByName(username);
    }

    @Override
    public User selectByName(String username) {
        return userMapper.selectByName(username);
    }

    @Override
    public PageUtil findUserAll(Page page) {
        PageUtil pageUtil=new PageUtil();

        PageHelper.startPage(page.getPage(),page.getLimit());
        List<User> userList=userMapper.findUserAll(page);
        PageInfo<User> pageInfo=new PageInfo<>(userList);
        pageUtil.setData(pageInfo.getList());
        pageUtil.setCount(pageInfo.getTotal());
        return pageUtil;
    }

    @Transactional
    @Override
    public void updateStatus(Integer userId,boolean flag) {
        userMapper.updateStatus(userId,flag);
    }

    @Override
    public PageUtil findAdminAll(Page page) {
        PageUtil pageUtil=new PageUtil();

        PageHelper.startPage(page.getPage(),page.getLimit());
        List<User> userList=userMapper.selectAll(page);
        PageInfo<User> pageInfo=new PageInfo<>(userList);
        pageUtil.setData(pageInfo.getList());
        pageUtil.setCount(pageInfo.getTotal());
        return pageUtil;
    }

    @Override
    @Transactional
    public void updateUser(User user) {

        user.setUpdateTime(new Date());
        userMapper.updateByPrimaryKey(user);
        // 更新关联角色
        this.userRoleMapper.delete(user.getUserId());//根据uid删除所有与用户管理的角色

        String[] roles = user.getRoleId().split(",");
        setUserRoles(user, roles);

        User currentUser = FebsUtil.getCurrentUser();
        if (StringUtils.equalsIgnoreCase(currentUser.getUsername(), user.getUsername())) {
            shiroRealm.clearAuthorizationInfoCache();
        }
    }

    @Override
    public void updatejd_user(User user) {
        user.setUpdateTime(new Date());
        userMapper.updatejd_user(user);
    }

    @Override
    public void userdelete(String[] id) {
        userMapper.userdelete(id);
    }

    @Override
    public void admindelete(String[] id) {
        userMapper.admindelete(id);
    }

    @Transactional
    @Override
    public void updateAvatar(String avatar, User user) {
        userMapper.updateAvatar(user.getUserId(),avatar);
    }

    @Override
    public void updateDetail(User user) {
        user.setUpdateTime(new Date());
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void updateLoginTime(String username) {
        userMapper.updateLoginTime(username,new Date());
    }

    private void setUserRoles(User user, String[] roles) {
        List<UserRole> userRoles = new ArrayList<>();
        Arrays.stream(roles).forEach(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(Integer.valueOf(roleId));
            userRoles.add(userRole);
        });
        userRoleMapper.insert(userRoles);
    }

}
