package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;

public interface LogDao {
    void logError(User user, Account account, String logType, String message, Exception ex);

    void logAudit(User user, Account account, String auditCode, String message);
}
