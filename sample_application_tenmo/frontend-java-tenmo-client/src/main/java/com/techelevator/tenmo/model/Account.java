package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private long id;
    private BigDecimal balance;
    private User user;
    public Account(){

    }
    public Account(long id, BigDecimal balance, User user) {
        this.id = id;
        this.balance = balance;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && balance.equals(account.balance) && user.equals(account.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, user);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", user=" + user +
                '}';
    }
}
