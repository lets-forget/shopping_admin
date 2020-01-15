package com.ning.home_admin.commons.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Page {
    Integer page;//当前页
    Integer limit;//每页显示的条数
    String username;//搜索的用户名
    String operation;//操作的描述
    String startTime;//开始的时间
    String endTime;//结束的时间
    String orderId;//搜索的订单号
    Integer pay;//使用什么支付
    Integer status;//订单状态
    String sortName;//排序的字段
    String sortType;//排序的类型
    Integer itemId;
    String title;
    Integer cId;
    Integer stats;//商品状态
}
