package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.*;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Mappers {
    public static User mapRowToUser(SqlRowSet rs, boolean includePassword, String ending) {
        User user = new User();
        if (ending.isEmpty()) {
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
        } else if (ending.equals("-")) {
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
        } else {
            user.setId(rs.getInt("user_id" + ending));
            user.setUsername(rs.getString("username" + ending));
        }
        if (includePassword) {
            user.setPassword(rs.getString("password_hash"));
        }
        user.setActivated(true);
        user.setAuthorities("USER");
        return user;
    }
    public static List<Account> mapRowsToAccounts(SqlRowSet rs) {
        List<Account> accounts = new ArrayList<>();
        while (rs.next()) {
            accounts.add(mapRowToAccount(rs,true,"", false));
        }
        return accounts;
    }
    public static Account mapRowToAccount(SqlRowSet rs, boolean includeBalance) {
        return mapRowToAccount(rs,true,"", includeBalance);
    }
    public static Account mapRowToAccount(SqlRowSet rs,boolean includeUser, String ending, boolean includeBalance) {
        Account account = new Account();
        if (ending.isEmpty()) {
            account.setId(rs.getInt("account_id"));
        } else {
            account.setId(rs.getInt("account" + ending));
        }
        if (includeBalance) account.setBalance(rs.getBigDecimal("balance" + ending));

        account.setUser(mapRowToUser(rs,false,ending));

        if (includeUser) account.setUser(mapRowToUser(rs, false, ending));
        return account;
    }
    public static TransferStatus mapRowToTransferStatus(SqlRowSet rs) {
        return new TransferStatus(rs.getInt("transfer_status_id"),rs.getString("transfer_status_desc"));
    }
    public static List<TransferStatus> mapRowsToTransferStatuses(SqlRowSet rs){
        List<TransferStatus> statuses = new ArrayList<>();
        while (rs.next()){
            statuses.add(mapRowToTransferStatus(rs));
        }
        return statuses;
    }
    public static TransferType mapRowToTransferType(SqlRowSet rs) {
        return new TransferType(rs.getInt("transfer_type_id"),rs.getString("transfer_type_desc"));
    }
    public static List<Transfer> mapRowToTransfers(SqlRowSet rs) {
        List<Transfer> transfers = new ArrayList<>();
        while (rs.next()){
            transfers.add( mapRowToTransfer(rs, false));
        }
        return transfers;
    }

    public static Transfer mapRowToTransfer(SqlRowSet rs, boolean includeBalance) {
        Transfer transfer = new Transfer();
        transfer.setId(rs.getInt("transfer_id"));
        transfer.setTransferType(mapRowToTransferType(rs));
        transfer.setTransferStatus(mapRowToTransferStatus(rs));
        transfer.setAccountFrom(mapRowToAccount(rs,true, "_from", includeBalance));
        transfer.setAccountTo(mapRowToAccount(rs,true,"_to", includeBalance));
        transfer.setAmount(rs.getBigDecimal("amount"));
        if (rs.getTimestamp("created_date")!= null){
            transfer.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
        }

        transfer.setAccountBalanceTo(rs.getBigDecimal("transfer_balance_to"));
        transfer.setAccountBalanceFrom(rs.getBigDecimal("transfer_balance_from"));
        transfer.setFee(rs.getBigDecimal("fee"));
        transfer.setMessage(rs.getString("transfer_message"));


        return transfer;
    }


}
