package com.ning.home_admin.monitor.entity;

import com.ning.home_admin.commons.utils.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@Data
@Slf4j
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;//操作人

    private Long time;//耗时时间

    private String ip;//操作的IP

    private Date createTime;//操作的时间

    private String location;//操作的地点

    private String operation;//操作的内容

    private String method;//操作的方法

    private String params;//操作的参数

    private String cteateTimeStr;

    public String getCteateTimeStr() {
        cteateTimeStr = DateUtil.formatFullTime(createTime);
        return cteateTimeStr;
    }

    public void setCteateTimeStr(String cteateTimeStr) {
        this.cteateTimeStr = cteateTimeStr;
    }
}