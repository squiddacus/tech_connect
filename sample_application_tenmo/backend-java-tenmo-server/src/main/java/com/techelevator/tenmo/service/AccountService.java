package com.techelevator.tenmo.service;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;

import java.util.List;

public interface AccountService {
    //	User can only get details on their own
    Account getByUser(User user);

    List<Account> getAccountListByUser(User user);
}
