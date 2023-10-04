package com.techelevator.tenmo.service;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;
    private final LogService logService;

    public AccountServiceImpl(AccountDao accountDao, LogService logService){
        this.accountDao = accountDao;
        this.logService = logService;
    }

    @Override
    public Account getByUser(User user) {
        Account account = accountDao.getAccount(user.getId());
        logService.logAudit(user, account, "RetrieveAccountInfo",
                "User: " +user.getUsername() + " retrieved account info:" + account.getId());
        return account;
    }

    @Override
    public List<Account> getAccountListByUser(User user) {
        return accountDao.getAccountsByUser(user.getId());
    }
}
