package com.ning.home_admin.sytem.mapper;



import com.ning.home_admin.sytem.pojo.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(Category record);

    Category selectByPrimaryKey(Integer cId);

    List<Category> selectAll();

    int updateByPrimaryKey(Category record);
}