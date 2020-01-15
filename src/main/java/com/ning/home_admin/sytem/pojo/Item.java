package com.ning.home_admin.sytem.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Item  {
    private Integer id;

    private Integer itemCid;

    private String itemTitle;

    private Double itemPrice;

    private String itemImage;

    private Integer itemSales;

    private Integer itemStats;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date itemCreatime;

    private String c_name;

}