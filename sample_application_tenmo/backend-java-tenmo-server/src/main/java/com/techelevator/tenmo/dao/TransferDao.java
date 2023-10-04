package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.tenmo.model.User;

import java.util.List;

public interface TransferDao {


    //	Get list by user
    //	Get list by user by status
    List<Transfer> getTransfersByUser(long userId, boolean isPending);

    //	Get detail by id
    Transfer getTransferById(int id,boolean includeBalance);

    //	Insert new transfer
    Transfer createTransfer(Transfer transfer);

    //	Update existing transfer
    Transfer updateTransfer(Transfer transfer);

    //list all transfer statuses (for account)
    List<TransferStatus> getTransferStatuses(User user);

}
