package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.util.List;

public interface AccountDao {
    //	Get by user id
    Account getAccount(long userId);

    Account getAccountByAccount(int accountId);

    //	Get list (include users)
    List<Account> getAccountsByUser(long userId);
    //	Update balance
    Account updateAccount(Account account);
}
