package com.ning.home_admin.sytem.pojo;


public class Category {
    private Integer cId;

    private String cName;

    private String cHref;

    @Override
    public String toString() {
        return "Category{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", cHref='" + cHref + '\'' +
                '}';
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcHref() {
        return cHref;
    }

    public void setcHref(String cHref) {
        this.cHref = cHref;
    }
}