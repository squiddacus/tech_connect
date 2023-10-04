package com.techelevator.tenmo.service;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.exception.TransferException;
import com.techelevator.tenmo.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferDao transferDao;
    private final AccountDao accountDao;
    private final LogService logService;

    public TransferServiceImpl(TransferDao transferDao, AccountDao accountDao, LogService logService){
        this.transferDao = transferDao;
        this.accountDao = accountDao;
        this.logService = logService;
    }


    @Override
    public List<Transfer> getTransferHistory(User user) {
        List<Transfer> transfers = transferDao.getTransfersByUser(user.getId(), false);
        for (Transfer transfer : transfers) {
            if (user.getId()== transfer.getAccountTo().getUser().getId()) {
                transfer.setAccountBalanceFrom(null);
            } else {
                transfer.setAccountBalanceTo(null);
            }
        }
        return transfers;
    }

    @Override
    public Transfer getTransferById(User user, int id) {
        Transfer transfer = transferDao.getTransferById(id, false);
        if ((transfer.getAccountTo().getUser().getId() != user.getId()) && (transfer.getAccountFrom().getUser().getId() != user.getId())) {
            throw new TransferException(transfer, "User is not permitted to view this transfer");
        }
        return transfer;
    }

    @Override
    public List<Transfer> getTransferPending(User user) {
        return transferDao.getTransfersByUser(user.getId(), true);
    }

    @Override
    public Transfer createTransfer(User user, Transfer transfer) {
        Account userAccount = accountDao.getAccount(user.getId());
        Account fromAccount = accountDao.getAccountByAccount(transfer.getAccountFrom().getId());
        Account toAccount = accountDao.getAccountByAccount(transfer.getAccountTo().getId());

        //check types and account matching
        if (transfer.getTransferType().getId() == 1) { //req
            if (!userAccount.equals(toAccount)) {
                throw new TransferException(transfer,"You cannot request TEbucks on behalf of another user");
            }
            //set the status based on the type
            transfer.setTransferStatus(new TransferStatus(1,"Pending"));
        } else if (transfer.getTransferType().getId() == 2) { //send
            if (!userAccount.equals(fromAccount)) {
                throw new TransferException(transfer,"You cannot send TEbucks on behalf of another user");
            }
            //set the status based on the type
            transfer.setTransferStatus(new TransferStatus(2,"Approved"));
        } else {
            throw new TransferException(transfer,"Invalid transfer type");
        }
        //if the balance is not high enough throw exc
        if (fromAccount.getBalance().compareTo(transfer.getAmount()) < 0) {
            throw new TransferException(transfer,"Balance is not high enough to transfer funds");
        }

        //set the transfer fee and other values to be inserted
        transfer.setFee(BigDecimal.valueOf(1));


        //call and update new balances
        updateAccountBalancesBasedOnFeeAndAmount(toAccount, fromAccount, transfer.getAmount(), transfer.getFee());

        transfer.setAccountBalanceTo(toAccount.getBalance());
        transfer.setAccountBalanceFrom(fromAccount.getBalance());

        //insert new transfer
        Transfer newTransfer = transferDao.createTransfer(transfer);

        //if this is approved
        if (newTransfer.getTransferStatus().getId() == 2) {
            //update account balances
            updateBalancesInAccounts(toAccount, fromAccount, transfer.getAmount());
        }
        logService.logAudit(user, userAccount,"SuccessfulTransfer", "Transfer id: (" + newTransfer.getId() + ") created" );
        return newTransfer;
    }

    private void updateAccountBalancesBasedOnFeeAndAmount(Account toAccount, Account fromAccount, BigDecimal amount, BigDecimal fee){
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        BigDecimal amountMinusFee = amount.subtract(fee);
        toAccount.setBalance(toAccount.getBalance().add(amountMinusFee));
    }
    private void updateBalancesInAccounts(Account toAccount, Account fromAccount, BigDecimal amount){
        accountDao.updateAccount(toAccount);
        accountDao.updateAccount(fromAccount);
    }

    @Override
    public Transfer updateTransferStatus(User user, Transfer transfer) {
        //interrogate the transfer to see the status being sent in

        if (transfer == null) throw new IllegalArgumentException("Transfer cannot be null");

        if (transfer.getTransferStatus()==null) throw new IllegalArgumentException("TransferStatus cannot be null");

        //if approved send to approval method
        if (TransferStatuses.Approved.equalsStatus(transfer.getTransferStatus())) {
            return approveTransfer(user,transfer.getId());
        } else if (TransferStatuses.Rejected.equalsStatus(transfer.getTransferStatus())) {
            return rejectTransfer(user,transfer.getId());
        } else {
            throw new TransferException(transfer,"Invalid status for update");
        }
    }


    private Transfer approveTransfer(User user, int transferId) {
        Account userAccount = accountDao.getAccount(user.getId());
        Transfer transfer = transferDao.getTransferById(transferId, true);
        //check to see that the request was made to user
        if (!transfer.getAccountFrom().equals(userAccount)) {
            throw new TransferException(transfer, "You can only approve requests made to you");
        }

        //verify that the transfer is still pending
        if (transfer.getTransferStatus().getId() != 1) {
            throw new TransferException(transfer, "The transfer must be in a pending status to approve");
        }
        //check to see that the request was made on an account with enough of a balance
        if (userAccount.getBalance().compareTo(transfer.getAmount()) < 0) {
            throw new TransferException(transfer, "The balance of the from account is to low");
        }
        transfer.setTransferStatus(new TransferStatus(2,"Approved"));
        Transfer newTransfer = transferDao.updateTransfer(transfer);
        updateBalancesInAccounts(transfer.getAccountTo(), transfer.getAccountFrom(), transfer.getAmount());
        logService.logAudit(user, userAccount,"TransferApproved", "Transfer id: (" + transfer.getId() + ") approved" );
        return newTransfer;
    }

    private Transfer rejectTransfer(User user, int transferId) {
        Account userAccount = accountDao.getAccount(user.getId());
        Transfer transfer = transferDao.getTransferById(transferId, true);
        //check to see that the request was made to user
        if (!transfer.getAccountFrom().equals(userAccount)) {
            throw new TransferException(transfer, "You can only approve requests made to you");
        }
        //verify that the transfer is still pending
        if (transfer.getTransferStatus().getId() != 1) {
            throw new TransferException(transfer, "The transfer must be in a pending status to approve");
        }
        //update to rejected
        transfer.setTransferStatus(new TransferStatus(3,"Rejected"));
        Transfer newTransfer = transferDao.updateTransfer(transfer);
        logService.logAudit(user, userAccount,"TransferRejected", "Transfer id: (" + transfer.getId() + ") rejected" );
        return newTransfer;
    }


    @Override
    public List<TransferStatus> getAllTransferStatuses(User user) {
        return transferDao.getTransferStatuses(user);
    }

}
