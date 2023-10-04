package com.techelevator.tenmo.service;

import com.techelevator.tenmo.dao.LogDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    private LogDao logDao;
    public LogServiceImpl(LogDao logDao){
        this.logDao = logDao;
    }
    @Override
    public void logError(User user, Account account, String errorMessage){
        logDao.logError(user,account,"Error",errorMessage,null);
    }
    @Override
    public void logError(User user, Account account, String errorMessage, Exception ex){
        logDao.logError(user,account,"Error",errorMessage,ex);
    }
    @Override
    public void logInfo(User user, Account account, String message){
        logDao.logError(user,account,"Info",message,null);
    }
    @Override
    public void logAudit(User user, Account account, String auditCode, String message){
        logDao.logAudit(user,account,auditCode,message);
    }
}
