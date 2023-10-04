package com.techelevator.tenmo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class Transfer {
    private long id;
    @JsonProperty("transferType")
    private TransferType type;
    @JsonProperty("transferStatus")
    private TransferStatus status;
    @JsonProperty("accountFrom")
    private Account fromAccount;
    @JsonProperty("accountTo")
    private Account toAccount;
    private BigDecimal amount;
    public Transfer(){}
    public Transfer(Account fromAccount, Account toAccount, BigDecimal amount,String typeDescription){
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.type = new TransferType(typeDescription);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TransferType getType() {
        return type;
    }

    public void setType(TransferType type) {
        this.type = type;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer = (Transfer) o;
        return id == transfer.id && Objects.equals(type, transfer.type) && Objects.equals(status, transfer.status) && Objects.equals(fromAccount, transfer.fromAccount) && Objects.equals(toAccount, transfer.toAccount) && Objects.equals(amount, transfer.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, status, fromAccount, toAccount, amount);
    }
}
