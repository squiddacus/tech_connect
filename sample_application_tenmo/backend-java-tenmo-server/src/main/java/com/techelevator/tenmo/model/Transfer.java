package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.nio.Buffer;
import java.time.LocalDateTime;

public class Transfer {
    private int id;
    private Account accountFrom;
    private Account accountTo;
    private BigDecimal amount;

    private TransferStatus transferStatus;
    private TransferType transferType;

    private BigDecimal fee;
    private String message;

    private BigDecimal accountBalanceFrom;
    private BigDecimal accountBalanceTo;


    public BigDecimal getEndingBalance(){
        return (accountBalanceFrom == null) ? accountBalanceTo : accountBalanceFrom;
    }
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getAccountBalanceFrom() {
        return accountBalanceFrom;
    }

    public void setAccountBalanceFrom(BigDecimal accountBalanceFrom) {
        this.accountBalanceFrom = accountBalanceFrom;
    }

    public BigDecimal getAccountBalanceTo() {
        return accountBalanceTo;
    }

    public void setAccountBalanceTo(BigDecimal accountBalanceTo) {
        this.accountBalanceTo = accountBalanceTo;
    }

    private LocalDateTime createdDate;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Transfer() {
    }

    public Transfer(int id, Account accountFrom, Account accountTo, BigDecimal amount) {
        this.id = id;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransferStatus getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(TransferStatus transferStatus) {
        this.transferStatus = transferStatus;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }
}
