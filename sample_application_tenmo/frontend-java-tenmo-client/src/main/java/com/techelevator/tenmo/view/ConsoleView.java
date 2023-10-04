package com.techelevator.tenmo.view;


import com.techelevator.tenmo.model.*;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleView {

    private final Scanner scanner = new Scanner(System.in);

    private AsciiArt icons;

    public ConsoleView(AsciiArt icons){
        this.icons = icons;
    }
    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        for (String line: icons.getIcon("TEnmoBank")) {
            System.out.println(AnsiColors.cyanValue(line,false));
        }
        System.out.println(AnsiColors.cyanValue("*********************",false));
        System.out.println("* Welcome to TEnmo! *");
        System.out.println(AnsiColors.cyanValue("*********************",true));
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println(AnsiColors.yellowValue("1",true) + AnsiColors.greenValue(": Register",true));
        System.out.println(AnsiColors.yellowValue("2",true) + AnsiColors.greenValue(": Login",true));
        System.out.println(AnsiColors.yellowValue("0",true) + AnsiColors.redValue(": Exit",true));
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println(AnsiColors.yellowValue("1",true) + AnsiColors.greenValue(": View your current balance",true));
        System.out.println(AnsiColors.yellowValue("2",true) + AnsiColors.greenValue(": View your past transfers",true));
        System.out.println(AnsiColors.yellowValue("3",true) + AnsiColors.greenValue(": View your pending requests",true));
        System.out.println(AnsiColors.yellowValue("4",true) + AnsiColors.greenValue(": Send TE bucks",true));
        System.out.println(AnsiColors.yellowValue("5",true) + AnsiColors.greenValue(": Request TE bucks",true));
        System.out.println(AnsiColors.yellowValue("0",true) + AnsiColors.redValue(": Exit",true));
        System.out.println();
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString(AnsiColors.yellowValue("Username: ",false));
        String password = promptForString(AnsiColors.yellowValue("Password: ",false));
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(AnsiColors.yellowValue(prompt,true));
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(AnsiColors.yellowValue("Please enter a number.",true));
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(AnsiColors.yellowValue("Please enter a decimal number.",true));
            }
        }
    }

    public void pause() {
        System.out.println(AnsiColors.yellowValue("\nPress Enter to continue...",true));
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }
    public void printMessage(String message) {
        System.out.println(AnsiColors.ANSI_BLUE);
        System.out.println(message);
        System.out.println(AnsiColors.ANSI_RESET);
    }

    public void printUserMenu(User[] users) {
        System.out.print(AnsiColors.ANSI_WHITE);
        printDivider(20);
        System.out.println("Users");
        System.out.printf("%-10s%s%n","ID","Name");
        printDivider(20);
        System.out.print(AnsiColors.ANSI_RESET);

        for(User u : users) {
            System.out.printf("%-10s%s%n",u.getId(),u.getUsername());
        }
        printDivider(10);
        System.out.println();
    }

    public void printDivider(int length) {
        for(int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void printAccountMenu(Account[] accounts) {
        System.out.print(AnsiColors.ANSI_WHITE);
        printDivider(20);
        System.out.println("Accounts");
        System.out.printf("%-10s%s%n","ID","Name");
        printDivider(20);
        System.out.print(AnsiColors.ANSI_RESET);

        for(Account a : accounts) {
            System.out.printf("%-10s%s%n",a.getId(),a.getUser().getUsername());
        }
        printDivider(20);
        System.out.println();
    }


    public void printTransferMenu(Transfer[] transfers, User currentUser) {
        System.out.print(AnsiColors.ANSI_CYAN);
        System.out.print(AnsiColors.ANSI_WHITE);
        printDivider(55);
        for (String line: icons.getIcon("transfers")) {
            System.out.println(AnsiColors.cyanValue(line,true));
        }
        System.out.print(AnsiColors.ANSI_CYAN);
        System.out.format("%-10s%-23s %-10s %-12s%n","ID","From/To","Amount","Status");
        printDivider(55);
        System.out.print(AnsiColors.ANSI_RESET);

        for(Transfer t : transfers) {
            String toFromString = formatTransfer(t, currentUser);
            System.out.print(getTransferColor(t, currentUser));
            System.out.format("%-10s%-23s %-10s %-12s%n",t.getId(),toFromString,t.getAmount(),t.getStatus().getDescription());
            System.out.print(AnsiColors.ANSI_RESET);
        }
        System.out.print(AnsiColors.ANSI_CYAN);
        printDivider(55);
        System.out.print(AnsiColors.ANSI_RESET);
        System.out.println();
    }

    public void printTransferDetails(Transfer transfer) {
        System.out.print(AnsiColors.ANSI_WHITE);
        printDivider(20);
        System.out.println("Transfer Details");
        printDivider(20);
        System.out.print(AnsiColors.ANSI_RESET);
        System.out.println("Id: "+AnsiColors.cyanValue(String.valueOf(transfer.getId()),true));
        System.out.println("From: "+AnsiColors.cyanValue(transfer.getFromAccount().getUser().getUsername(),true));
        System.out.println("To: "+AnsiColors.cyanValue(transfer.getToAccount().getUser().getUsername(),true));
        System.out.println("Type: "+AnsiColors.cyanValue(transfer.getType().getDescription(),true));
        System.out.println("Status: "+AnsiColors.cyanValue(transfer.getStatus().getDescription(),true));
        System.out.println("Amount: "+AnsiColors.cyanValue(transfer.getAmount().toString(),true));
    }

    private String formatTransfer(Transfer transfer, User currentUser) {
        String toFromString = null;
        if (currentUser.getId() == transfer.getFromAccount().getUser().getId()) {
            toFromString = "To: "+transfer.getToAccount().getUser().getUsername();
        } else {
            toFromString = "From: "+transfer.getFromAccount().getUser().getUsername();
        }
        return toFromString;
    }
    private String getTransferColor(Transfer transfer, User currentUser) {

        if (transfer.getStatus().getDescription().equals(TransferStatus.Pending)) {
            return AnsiColors.ANSI_PURPLE;
        } else if (currentUser.getId() == transfer.getFromAccount().getUser().getId()) {
            return AnsiColors.ANSI_RED;
        }
        return AnsiColors.ANSI_GREEN;
    }

}
