package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.service.LogService;
import com.techelevator.tenmo.service.TransferService;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.tenmo.model.TransferType;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
@RequestMapping("/api")
public class TransferController extends BaseController {

    private final TransferService transferService;

    public TransferController(TransferService transferService, LogService logService) {
        super(logService);
        this.transferService = transferService;
    }

    //o	List of transfer types (GET /transfertype)
    @RequestMapping(path = "transfertype", method = RequestMethod.GET)
    public List<TransferType> getTransferTypesForUser(Principal principal) {
        logAPICall("HTTP GET request received for user " + principal.getName() + " with URL /transfertype ");
        return null;
    }

    //o	List of transfers statuses (GET /transferstatus)
    @RequestMapping(path = "transferstatus", method = RequestMethod.GET)
    public List<TransferStatus> getTransferStatusesForUser(Principal principal) {
        logAPICall("HTTP GET request received for user " + principal.getName() + " with URL /transferstatus ");
        return null;
    }

    //o	List of transfers (GET /transfer)
    @RequestMapping(path = "transfer", method = RequestMethod.GET)
    public List<Transfer> getTransfersForUser(Principal principal) {
        logAPICall("HTTP GET request received for user " + principal.getName() + " with URL /transfer ");

        return transferService.getTransferHistory(super.getUserFromPrincipal(principal));
    }

    @RequestMapping(path = "pending/transfer", method = RequestMethod.GET)
    public List<Transfer> getPendingTransfersForUser(Principal principal) {
        logAPICall("HTTP GET request received for user " + principal.getName() + " with URL /pending/transfer ");
        return transferService.getTransferPending(super.getUserFromPrincipal(principal));
    }

    //o	Detail of a single transfer (GET /transfer/{Id})
    @RequestMapping(path = "transfer/{transferId}", method = RequestMethod.GET)
    public Transfer getTransferForUser(@PathVariable int transferId, Principal principal) {
        logAPICall("HTTP GET request received for user " + principal.getName() + " with URL /transfer/" + transferId);
        return transferService.getTransferById(super.getUserFromPrincipal(principal), transferId);
    }

    //o	Create a single transfer (status for pending) (POST /transfer)
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "transfer", method = RequestMethod.POST)
    public Transfer createTransfer(@Valid @RequestBody Transfer transfer, Principal principal) {
        logAPICall("HTTP POST request received for user " + principal.getName() + " with URL /transfer \n"
                + " with Transfer object: " + transfer);
        return transferService.createTransfer(super.getUserFromPrincipal(principal), transfer);
    }

    //o	Update a single transfer (update the status) (PUT /transfer/{Id})
    @RequestMapping(path = "transfer/{transferId}", method = RequestMethod.PUT)
    public Transfer getTransfersForUser(@PathVariable int transferId, @Valid @RequestBody Transfer transfer, Principal principal) {
        logAPICall("HTTP PUT request received for user " + principal.getName() + " with URL /transfer/" + transferId + "\n"
                + " with Transfer object: " + transfer);
        if (transferId != transfer.getId()) throw new IllegalArgumentException("transfer id does not match transfer");
        return transferService.updateTransferStatus(super.getUserFromPrincipal(principal), transfer);
    }

    /**
     * Helper method to log API calls made to the server
     *
     * @param message - message to be included in the server log
     */
    public void logAPICall(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.A");
        String timeNow = now.format(formatter);
        System.out.println(timeNow + "-" + message);

    }
}
