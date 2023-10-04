package com.techelevator.tenmo.model;

public enum TransferStatuses {
    //1,Pending
    //2,Approved
    //3,Rejected
    Pending(1),
    Approved(2),
    Rejected(3);
    private final int status;
    private TransferStatuses(int status){
        this.status = status;
    }
    public boolean equalsStatus(int compare){
        return compare == status;
    }

    public boolean equalsStatus(TransferStatus compare){
        return compare.getId() == status;
    }

    public String toString(){
        return String.valueOf(status);
    }
}
