package com.techelevator.handler;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.exception.TransferException;
import com.techelevator.tenmo.service.LogService;
import com.techelevator.tenmo.service.TransferServiceImpl;
import com.techelevator.tenmo.service.TransferService;
import com.techelevator.tenmo.model.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferServiceImplTests {
    TransferDao transferDao = new TransactionDaoMock();
    AccountDao accountDao = new AccountDaoMock();
    LogService logService = new LogHandlerMock();
    TransferService cut;
    Transfer testTransfer1;

    @Before
    public void setup() {
        cut = new TransferServiceImpl(transferDao, accountDao, logService);
        testTransfer1 = setupTransfer();
    }

    private Transfer setupTransfer(){
        Transfer testTransfer = new Transfer();
        testTransfer.setTransferStatus(new TransferStatus(1,"Approved"));
        testTransfer.setTransferType(new TransferType(2,"Send"));
        Account toAccount = new Account();
        toAccount.setBalance(BigDecimal.valueOf(100.00));
        toAccount.setId(1);
        toAccount.setUser(new User(99,"test","",null));
        Account fromAccount = new Account();
        fromAccount.setBalance(BigDecimal.valueOf(100.00));
        fromAccount.setId(1);
        fromAccount.setUser(new User(101,"2","",null));
        testTransfer.setAccountTo(toAccount);
        testTransfer.setAccountFrom(fromAccount);
        ((AccountDaoMock)this.accountDao).AccountList.put(toAccount.getId(), toAccount);
        ((AccountDaoMock)this.accountDao).AccountList.put(fromAccount.getId(), fromAccount);
        ((AccountDaoMock)this.accountDao).AccountListByUser.put(fromAccount.getUser().getId(), fromAccount);
        ((AccountDaoMock)this.accountDao).AccountListByUser.put(toAccount.getUser().getId(), toAccount);
        return testTransfer;
    }

    @Test
    public void transaction_create_baduser(){
        testTransfer1.setAmount(BigDecimal.valueOf(99999));
        TransferException thrown = assertThrows(
                TransferException.class,
                () -> cut.createTransfer(testTransfer1.getAccountTo().getUser(), testTransfer1),
                "Expected createTransfer() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contentEquals("Balance is not high enough to transfer funds - From: 2, To: test"));
    }




}
class LogHandlerMock implements LogService {


    @Override
    public void logError(User user, Account account, String errorMessage, Exception ex) {

    }

    @Override
    public void logError(User user, Account account, String errorMessage) {

    }

    @Override
    public void logInfo(User user, Account account, String message) {

    }

    @Override
    public void logAudit(User user, Account account, String auditCode, String message) {

    }
}
class TransactionDaoMock implements TransferDao{

    @Override
    public List<Transfer> getTransfersByUser(long userId, boolean isPending) {
        return null;
    }

    @Override
    public Transfer getTransferById(int id, boolean includeBalance) {
        return null;
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        return null;
    }

    @Override
    public Transfer updateTransfer(Transfer transfer) {
        return null;
    }

    @Override
    public List<TransferStatus> getTransferStatuses(User user) {
        return null;
    }
}
class AccountDaoMock implements AccountDao {

    public Map<Integer,Account> AccountList = new HashMap<>();
    public Map<Long,Account> AccountListByUser = new HashMap<>();

    @Override
    public Account getAccount(long userId) {
        return AccountListByUser.get(userId);
    }

    @Override
    public Account getAccountByAccount(int accountId) {
        return AccountList.get(accountId);
    }

    @Override
    public List<Account> getAccountsByUser(long userId) {
        return null;
    }

    @Override
    public Account updateAccount(Account account) {
        return null;
    }
}
