package com.techelevator.tenmo.exception;

import com.techelevator.tenmo.model.Transfer;

public class TransferException extends RuntimeException {
    private Transfer transfer;
    public TransferException(Transfer transfer, String message){
        super(message);
        this.transfer = transfer;
    }
    public String getMessage(){
        String usernameTo = ((transfer.getAccountTo()!=null) && transfer.getAccountTo().getUser() != null) ? transfer.getAccountTo().getUser().getUsername() : "unknown";
        String usernameFrom = ((transfer.getAccountFrom()!=null) && transfer.getAccountFrom().getUser() != null) ? transfer.getAccountFrom().getUser().getUsername() : "unknown";
        return super.getMessage() + ((transfer==null) ? "" : " - From: " + usernameFrom + ", To: " + usernameTo);
    }
}
