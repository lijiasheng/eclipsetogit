package com.simplebank.model;

import java.math.BigDecimal;

/**
 * Data Object, 属性和数据库表的column一一对应
 */
public class AccountDO {
    private Integer id;
    private String accountNo;
    private String name;
    private String userId;
    private BigDecimal balance;
    private String ccy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    @Override
    public String toString() {
        return "AccountDO{" +
                "id=" + id +
                ", accountNo='" + accountNo + '\''  +
                ", name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", balance=" + balance +
                ", ccy='" + ccy + '\'' +
                '}';
    }
}
