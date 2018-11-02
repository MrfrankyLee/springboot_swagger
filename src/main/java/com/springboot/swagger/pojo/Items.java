package com.springboot.swagger.pojo;

import java.util.Date;

/**
 * @author lixiaole
 * @date 2018/11/2
 * @Description
 */
public class Items {
    /** 商品表主键Id */
    private Integer id;
    /** 商品名称 */
    private String itemsName;
    /** 商品定价 */
    private float price;
    /** 商品描述 */
    private String detail;
    /** 商品图片 */
    private String picture;
    /** 生产日期 */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
