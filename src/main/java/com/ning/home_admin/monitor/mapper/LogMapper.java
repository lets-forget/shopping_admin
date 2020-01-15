package com.ning.home_admin.monitor.mapper;

import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.monitor.entity.Log;

import java.util.List;

public interface LogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    Log selectByPrimaryKey(Integer id);

    List<Log> selectAll(Page page);

    int updateByPrimaryKey(Log record);

    void deleteAllByIds(String[] logIds);
}