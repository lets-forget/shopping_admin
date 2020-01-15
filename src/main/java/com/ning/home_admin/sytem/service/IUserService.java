package com.ning.home_admin.sytem.service;


import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.commons.utils.PageUtil;
import com.ning.home_admin.sytem.pojo.User;

/**
 * @author MrBird
 */
public interface IUserService {

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    User findByName(String username);

    /**
     * 查询jd_user的用户
     *
     */
    User selectByName(String username);
    /**
     * 查询全部并分页
     * @param page
     * @return
     */
    PageUtil findUserAll(Page page);

    /**
     * 根据uid修改状态
     * @param userId
     */
    void updateStatus(Integer userId, boolean flag);

    PageUtil findAdminAll(Page page);

    /**
     * 修改用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 修改前台用户
     * @param user
     */
    void updatejd_user(User user);

    /**
     * 删除前端用户
     * @param id
     */
    void userdelete(String[] id);

    /**
     * 删除后台用户
     * @param id
     */
    void admindelete(String[] id);

    /**
     * 修改用户头像
     * @param avatar
     */
    void updateAvatar(String avatar, User user);

    /**
     * 修改信息
     * @param user
     */
    void updateDetail(User user);

    /**
     * 更新登录
     */
    void updateLoginTime(String username);
}
