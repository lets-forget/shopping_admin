package com.ning.home_admin.commons.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class PageUtil {

    private int code=0;
    private String msg;
    private Long count; //数据总数
    private List<?> data = new ArrayList(); //装前台当前页的数据
}
