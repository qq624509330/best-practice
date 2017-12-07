package io.spldeolin.bestpractice.input;

import java.math.BigDecimal;
import java.util.Date;

public class InteractionInput {

    private String name;

    private Date date;

    private BigDecimal money;

    private Integer no;

    private Long serial;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "InteractionEntity [name=" + name + ", date=" + date + ", money=" + money + ", no=" + no + ", serial="
                + serial + "]";
    }

}