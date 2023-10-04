package com.techelevator.tenmo.service;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.tenmo.model.User;

import java.util.List;

public interface TransferService {

    List<Transfer> getTransferHistory(User user);

    Transfer getTransferById(User user, int id);

    List<Transfer> getTransferPending(User user);

    //	Create a transfer
    Transfer createTransfer(User user, Transfer transfer);

    Transfer updateTransferStatus(User user, Transfer transfer);


    List<TransferStatus> getAllTransferStatuses(User user);
}
