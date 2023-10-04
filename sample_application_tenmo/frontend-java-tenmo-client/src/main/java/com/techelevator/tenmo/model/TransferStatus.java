package com.techelevator.tenmo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TransferStatus {
    public static TransferStatus Pending = new TransferStatus(1L,"Pending");
    public static TransferStatus Approved = new TransferStatus(2L, "Approved");
    public static TransferStatus Rejected  = new TransferStatus(3L,"Rejected");
    private long id;
    @JsonProperty("desc")
    private String description;
    private int numberOfTransfers;

    public TransferStatus() {

    }
    public TransferStatus(Long id) {
        this.id = id;
    }
    public TransferStatus(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfTransfers() {
        return numberOfTransfers;
    }

    public void setNumberOfTransfers(int numberOfTransfers) {
        this.numberOfTransfers = numberOfTransfers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferStatus that = (TransferStatus) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
