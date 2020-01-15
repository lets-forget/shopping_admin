package com.ning.home_admin.sytem.mapper;

import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.sytem.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    User selectByPrimaryKey(Integer userId);

    List<User> selectAll(Page page);

    int updateByPrimaryKey(User record);

    User findByName(String username);

    User selectByName(String username);

    List<User> findUserAll(Page page);

    void updateStatus(@Param("id") Integer userId, @Param("flag") boolean flag);

    void updatejd_user(User user);

    void userdelete(String[] id);

    void admindelete(String[] id);

    void updateAvatar(Integer uid, String avatar);

    void updateLoginTime(String username, Date date);
}