package com.ning.home_admin.sytem.pojo;

import com.ning.home_admin.commons.utils.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@Slf4j
public class Role {
    private Integer roleId;

    private String roleName;

    private String remark;

    private Date createTime;

    private Date updateTime;

    /**
     * 角色对应的菜单（按钮） id
     */
    private transient String menuIds;

    private String createTimeStr;

    public String getCreateTimeStr() {
        if (createTime!=null)createTimeStr=DateUtil.formatFullTime(createTime);
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}