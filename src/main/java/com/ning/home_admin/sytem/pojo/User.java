package com.ning.home_admin.sytem.pojo;


import com.ning.home_admin.commons.utils.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable{


    private Integer userId;

    private String username;

    private String password;

    private String email;

    private Integer sex;

    private String phone;

    private Integer status;

    private Integer activate;

    private Date createTime;

    private Date updateTime;

    private Date lastTime;

    private String avatar;

    private String describes;

    private String code;

    private String roleId;

    private String roleName;

    public Integer getId() {
        return userId;
    }

    public String createTimeStr;

    public String getCreateTimeStr() {
        if (createTime!=null)createTimeStr= DateUtil.formatFullTime(createTime);
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}