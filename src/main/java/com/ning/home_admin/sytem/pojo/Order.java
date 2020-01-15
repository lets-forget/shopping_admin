package com.ning.home_admin.sytem.pojo;

public class Order {
    private Integer id;

    private Integer orderUid;

    private String orderId;

    private Integer orderAddressId;

    private String orderDetailsId;

    private String orderClassify;

    private String orderTitle;

    private Integer orderCount;

    private String orderImage;

    private Double orderPrice;

    private Double orderSubTotal;

    private String orderTime;

    private Integer orderShouh;

    private Integer orderIspay;

    private Integer orderType;

    private String orderShouhStr;

    private String orderIspayStr;

    private String orderTypeStr;

    private String name;

    private String telephone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getOrderShouhStr() {
        if (orderShouh==1){
            orderShouhStr="待评价";
        }
        if (orderShouh==0){
            orderShouhStr="待收货";
        }
        return orderShouhStr;
    }

    public void setOrderShouhStr(String orderShouhStr) {
        this.orderShouhStr = orderShouhStr;
    }

    public String getOrderIspayStr() {
        if (orderIspay==1){
            orderIspayStr="已支付";
        }
        if (orderIspay==0){
            orderIspayStr="待支付";
        }
        return orderIspayStr;
    }

    public void setOrderIspayStr(String orderIspayStr) {
        this.orderIspayStr = orderIspayStr;
    }

    public String getOrderTypeStr() {
        if (orderType==0){
            orderTypeStr="支付宝";
        }
        if (orderType==1){
            orderTypeStr="微信";
        }
        if (orderType==2){
            orderTypeStr="银联";
        }
        return orderTypeStr;
    }

    public void setOrderTypeStr(String orderTypeStr) {
        this.orderTypeStr = orderTypeStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderUid() {
        return orderUid;
    }

    public void setOrderUid(Integer orderUid) {
        this.orderUid = orderUid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(String orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public String getOrderClassify() {
        return orderClassify;
    }

    public void setOrderClassify(String orderClassify) {
        this.orderClassify = orderClassify == null ? null : orderClassify.trim();
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle == null ? null : orderTitle.trim();
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public String getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(String orderImage) {
        this.orderImage = orderImage == null ? null : orderImage.trim();
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderAddressId() {
        return orderAddressId;
    }

    public void setOrderAddressId(Integer orderAddressId) {
        this.orderAddressId = orderAddressId;
    }

    public Double getOrderSubTotal() {
        return orderSubTotal;
    }

    public void setOrderSubTotal(Double orderSubTotal) {
        this.orderSubTotal = orderSubTotal;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderShouh() {
        return orderShouh;
    }

    public void setOrderShouh(Integer orderShouh) {
        this.orderShouh = orderShouh;
    }

    public Integer getOrderIspay() {
        return orderIspay;
    }

    public void setOrderIspay(Integer orderIspay) {
        this.orderIspay = orderIspay;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderUid=" + orderUid +
                ", orderId='" + orderId + '\'' +
                ", orderClassify='" + orderClassify + '\'' +
                ", orderTitle='" + orderTitle + '\'' +
                ", orderCount=" + orderCount +
                ", orderImage='" + orderImage + '\'' +
                ", orderPrice=" + orderPrice +
                ", orderTime=" + orderTime +
                ", orderShouh=" + orderShouh +
                ", orderIspay=" + orderIspay +
                ", orderType=" + orderType +
                ", orderShouhStr='" + orderShouhStr + '\'' +
                ", orderIspayStr='" + orderIspayStr + '\'' +
                ", orderTypeStr='" + orderTypeStr + '\'' +
                '}';
    }
}