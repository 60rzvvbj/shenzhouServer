package com.ycx.shenzhou.pojo;

public class Gift {
    private String id; // 礼品编号
    private String name; // 礼品名称
    private String describe; // 礼品描述
    private int price; // 礼品价格
    private int status; // 礼品状态 0: 未兑换, 1: 已兑换

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}