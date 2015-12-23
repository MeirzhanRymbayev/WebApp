package kz.epam.mrymbayev.model;

import java.sql.Date;

public class Order extends BaseEntity {

    private Long voucherId;
    private Long userId;
    private Integer cost;
    private Integer discount;
    private Date date;
    private int amount;

    public void setVoucherId(long voucherId) {
        this.voucherId = voucherId;
    }

    public long getVoucherId() {
        return voucherId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCost() {
        return cost;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}