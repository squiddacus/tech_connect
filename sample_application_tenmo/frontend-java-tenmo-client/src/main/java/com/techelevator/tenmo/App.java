package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.*;
import com.techelevator.tenmo.view.AnsiColors;
import com.techelevator.tenmo.view.AsciiArt;
import com.techelevator.tenmo.view.ConsoleView;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

public class App {

    private static final String API_BASE_URL = "http://localhost:9000/api/";

    private final ConsoleView consoleView;
    private final AuthenticationService authenticationService;
    private final AccountService accountService;
    private final TransferService transferService;
    private final UserService userService;

    private AuthenticatedUser currentUser;
    private TransferStatus[] transferStatuses;
    private AsciiArt icons;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    public App(){
        authenticationService = new AuthenticationService(API_BASE_URL);
        accountService = new AccountService(API_BASE_URL);
        transferService = new TransferService(API_BASE_URL);
        userService = new UserService(API_BASE_URL);
        icons = new AsciiArt("art/ascii.txt");
        consoleView = new ConsoleView(icons);
    }
    private void run() {
        consoleView.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleView.printLoginMenu();
            menuSelection = consoleView.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleView.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleView.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleView.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleView.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser != null) {
            accountService.setAuthUser(currentUser);
            transferService.setAuthUser(currentUser);
            userService.setAuthUser(currentUser);
        } else {
            consoleView.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            System.out.println(this.currentUser.getUser().getUsername());
            consoleView.printMainMenu();
            menuSelection = consoleView.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleView.pause();
        }
    }

	private void viewCurrentBalance() {
        Account account = accountService.getCurrentAccount();
        if (account != null) {
            System.out.println("Your current account balance is: " + account.getBalance());
        } else {
            consoleView.printErrorMessage();
        }
	}

	private void viewTransferHistory() {
        Transfer[] transfers = transferService.getTransfers(null);
        if (transfers != null) {
            if (transfers.length > 0) {
                printTransfers(transfers);
            } else {
                consoleView.printMessage("No transfers to show");
            }
        } else {
            consoleView.printErrorMessage();
        }
	}

    private void viewPendingRequests() {
        Transfer[] transfers = transferService.getTransfers(TransferStatus.Pending);
        if (transfers != null) {
            if (transfers.length==0){
                consoleView.printMessage("YOu have no pending transfer.");
                return;
            }
            Transfer detail = printTransfers(transfers);
            if (detail != null) {
                if (detail.getFromAccount().getUser().equals(this.currentUser.getUser())) {
                    String action = consoleView.promptForString("(A)pprove, (R)eject, or (I)gnore: ");
                    if (action.equalsIgnoreCase("A")) {
                        handleApproval(detail);
                    } else if (action.equalsIgnoreCase("R")) {
                        handleRejection(detail);
                    }
                } else {
                    consoleView.printMessage(detail.getFromAccount().getUser().getUsername() + " - still has not approved or declined this request.");
                }
            }
        } else {
            consoleView.printErrorMessage();
        }
    }
    private Transfer printTransfers(Transfer[] transfers){
        consoleView.printTransferMenu(transfers, currentUser.getUser());
        int selectedTransferId = consoleView.promptForInt("Please enter transfer ID to view details (0 to cancel): ");
        if(selectedTransferId != 0) {
            Transfer transfer = transferService.getTransferDetails(selectedTransferId);
            if (transfer != null) {
                consoleView.printTransferDetails(transfer);
                return transfer;
            } else {
                consoleView.printErrorMessage();
            }
        }
        return null;
    }

    private void handleApproval(Transfer transfer) {
        transfer.setStatus(TransferStatus.Approved);
        if (transferService.approveOrDecline(transfer)) {
            Transfer newTransfer = transferService.getTransferDetails(transfer.getId());
            System.out.println("Approved transfer of " + newTransfer.getAmount() + " TE Bucks to " + newTransfer.getToAccount().getUser().getUsername());
        } else {
            consoleView.printErrorMessage();
        }
    }

    private void handleRejection(Transfer transfer) {
        transfer.setStatus(TransferStatus.Rejected);
        if (transferService.approveOrDecline(transfer)) {
            Transfer newTransfer = transferService.getTransferDetails(transfer.getId());
            System.out.println("Rejected transfer of " + newTransfer.getAmount() + " TE Bucks to " + newTransfer.getToAccount().getUser().getUsername());
        } else {
            consoleView.printErrorMessage();
        }
    }

    private void sendBucks() {
        Account[] accounts = accountService.getAccounts();

        if (accounts != null) {
            consoleView.printAccountMenu(accounts);
            int toAccountId = consoleView.promptForInt("Enter ID of user you are sending to (0 to cancel): ");
            if(toAccountId != 0) {
                Account toAccount = null;
                for (Account a: accounts) {
                    if (a.getId()==toAccountId) {
                        toAccount = a;
                    }
                }
                if (toAccount != null) {
                    BigDecimal amount = consoleView.promptForBigDecimal("Enter amount: ");
                    Account fromAccount = accountService.getCurrentAccount();
                    Transfer newTransfer = new Transfer(fromAccount, toAccount, amount, TransferType.Send);
                    Transfer transfer = transferService.createTransfer(newTransfer);
                    if (transfer != null) {
                        System.out.println(amount + " TE Bucks were sent to user " + toAccount.getUser().getUsername());
                    } else {
                        consoleView.printErrorMessage();
                    }
                }
            }
        } else {
            consoleView.printErrorMessage();
        }
    }

    private void requestBucks() {
        Account[] accounts = accountService.getAccounts();
        if (accounts != null) {
            consoleView.printAccountMenu(accounts);
            int fromAccountId = consoleView.promptForInt("Enter ID of user you are requesting from (0 to cancel): ");
            if(fromAccountId != 0) {
                Account fromAccount = null;
                for (Account a: accounts) {
                    if (a.getId()==fromAccountId) {
                        fromAccount = a;
                    }
                }
                if (fromAccount != null) {
                    BigDecimal amount = consoleView.promptForBigDecimal("Enter amount: ");
                    Account toAccount = accountService.getCurrentAccount();

                    Transfer newTransfer = new Transfer(fromAccount, toAccount,amount, TransferType.Request);
                    Transfer transfer = transferService.createTransfer(newTransfer);
                    if (transfer != null) {
                        System.out.println(amount+" TE Bucks were requested from user " + fromAccount.getUser().getUsername());
                    } else {
                        consoleView.printErrorMessage();
                    }
                }
            }
        } else {
            consoleView.printErrorMessage();
        }
    }
    private TransferStatus getStatus(String statusCode){
        if (this.transferStatuses == null) {
            this.transferStatuses = transferService.getTransferStatuses();
        }
        Optional<TransferStatus> status = Arrays.stream(transferStatuses)
                .filter(ts->ts.getDescription().equalsIgnoreCase(statusCode)).findFirst();
        return status.orElse(null);
    }

}
