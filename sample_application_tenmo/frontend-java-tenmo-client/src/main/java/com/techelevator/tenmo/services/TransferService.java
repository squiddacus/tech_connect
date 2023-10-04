package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.tenmo.model.TransferType;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

public class TransferService extends AuthenticatedApiService  {
    public TransferService(String baseUrl){
        super(baseUrl);
    }
    public Transfer createTransfer(Transfer newTransfer) {
        Transfer transfer = null;
        try {
            ResponseEntity<Transfer> response =
                    restTemplate.exchange(baseUrl + "transfer",
                            HttpMethod.POST,
                            new HttpEntity<>(newTransfer,getHeaders()),
                            Transfer.class);
            transfer = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfer;
    }

    public Transfer[] getTransfers(TransferStatus filter) {
        try {
            String path = baseUrl + (((filter!=null) && (filter.equals(TransferStatus.Pending)) ?
                    "pending/": "") + "transfer");
            ResponseEntity<Transfer[]> response =
                    restTemplate.exchange(path,
                            HttpMethod.GET,
                            makeAuthEntity(),
                            Transfer[].class);
            return response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return null;
    }
    public TransferStatus[] getTransferStatuses() {
        try {
            ResponseEntity<TransferStatus[]> response =
                    restTemplate.exchange(baseUrl + "transfer/status",
                            HttpMethod.GET,
                            makeAuthEntity(),
                            TransferStatus[].class);
            return response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return null;
    }
    public Transfer getTransferDetails(long selectedTransferId) {
        try {
            ResponseEntity<Transfer> response =
                    restTemplate.exchange(baseUrl + "transfer/" + selectedTransferId,
                            HttpMethod.GET,
                            makeAuthEntity(),
                            Transfer.class);
            return response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return null;
    }
    public boolean approveOrDecline(Transfer transfer) {
        try {
            restTemplate.exchange(baseUrl + "transfer/" + transfer.getId() ,
                            HttpMethod.PUT,
                            new HttpEntity<>(transfer,getHeaders()),
                            Transfer.class);
            return true;
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return false;
    }
}
