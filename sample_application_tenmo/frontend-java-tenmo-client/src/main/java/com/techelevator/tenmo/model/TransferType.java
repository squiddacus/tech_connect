package com.techelevator.tenmo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TransferType {
    public static String Request = "Request";
    public static String Send = "Send";
    private long id;
    @JsonProperty("desc")
    private String description;
    public TransferType() {}
    public TransferType(long id) {
        this.id = id;
    }
    public TransferType(String description) {
        this.description = description;
        this.id = (this.description.equals(Request) ? 1 : 2);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferType that = (TransferType) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
