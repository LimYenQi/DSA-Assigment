/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Lim Yen Qi
 */
import entity.Member;
import adt.DoublyLinkedList;
import adt.ListInterface;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EkaraOKAY {
    private Scanner scanner = new Scanner(System.in);
    private static final String REGEX = "^(.+)@(.+)$";
    private static final String ADMIN_PASSWORD = "ad123";
    private ListInterface<Member> memberList = new DoublyLinkedList<Member>();
    private Member currentMember = new Member();
    private int numOfMem;
    private String registered;
    
    
    public void initialization(){
        //(username, password, contactNum, email, bankAccNum, subsripPlan)
        Member m1 = new Member("David", "david123", "016-4563210", "david@gmail.com", "9876543210", "30 days");
        Member m2 = new Member("Tony", "tony456", "012-3456789", "tony@gmail.com", "4567890123", "90 days");
        Member m3 = new Member("Chris", "chris789", "013-6583198", "chris@gmail.com", "7894561023", "180 days");
        Member m4 = new Member("Peter", "peter321", "016-5698237", "peter@gmail.com", "5632189875", "180 days");
        Member m5 = new Member("Steve", "steve654", "017-6598352", "steve@gmail.com", "6985320158", "90 days");
        Member m6 = new Member("Wanda", "wanda987", "019-6301582", "wanda@gmail.com", "3659812053", "30 days");
        memberList.add(m1);
        numOfMem ++;
        memberList.add(m2);
        numOfMem ++;
        memberList.add(m3);
        numOfMem ++;
        memberList.add(m4);
        numOfMem ++;
        memberList.add(m5);
        numOfMem ++;
        memberList.add(m6);
        numOfMem ++;
    }
    
    public void welcomeMessages () {
        boolean proceed = false;
        
        System.out.println("\n");
        System.out.println("=============================================================");
        System.out.println("|\t\t\tWelcome to E-karaOKAY\t\t\t|");
        System.out.println("=============================================================\n");
        
        do {
            do {
                System.out.println("Are you a member of E-karaOKAY?");
                System.out.println("[1] - Yes and I would like to log in my account.");
                System.out.println("[2] - No and I would like to register an account.");
                System.out.print("(1 or 2): ");
                registered = scanner.nextLine();
                if (!(registered.equals("1")) && !(registered.equals("2")) && !(registered.equals(ADMIN_PASSWORD))) {
                    System.out.println("\nInvalid input. Please only enter 1 or 2.");
                }
            } while(!(registered.equals("1")) && !(registered.equals("2")) && !(registered.equals(ADMIN_PASSWORD)));

            switch (registered) {
                case "1":
                    proceed = login();
                    break;
                case "2":
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n==================== Member Registration ====================");
                    System.out.println("Please enter the following information to register.\n");
                    proceed = registration();
                    break;
                case ADMIN_PASSWORD:
                    proceed = true;
                default:
                    break;
            }
            if (!proceed) {
                System.out.println("\n\n\n\n\n\n\n\n\n");
            }
        } while(!proceed);
    }
    
    //inside welcomeMessages()
    public boolean login() {
        int count = 3, number = memberList.getSize();
        boolean mFound = false, uFound, pFound;
        Member mem = new Member();
        String username, password;
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n======================= Member Log In =======================");
        System.out.println("What is your username and password ?");
        do {
            uFound = false; 
            pFound = false;
            System.out.print("Username: ");
            username = scanner.nextLine();
            System.out.print("Password: ");
            password = scanner.nextLine();
            for (int i = 0; !mFound && i < number; i++) {
                if (username.equals(memberList.getEntry(i + 1).getUsername())) {
                    mem = memberList.getEntry(i + 1);
                    uFound = true;
                }
                if (mem != null && password.equals(mem.getPassword())) {
                    pFound = true;
                }
            }
            count--;
            if (uFound && pFound) {
                mFound = true;
            } else if (!uFound) {
                System.out.println("\nInvalid username!! Attempt left: " + count);
            } else if (!pFound) {
                System.out.println("\nInvalid password!! Attempt left: " + count);
            }
        } while(!mFound && count != 0);
        currentMember = mem;
        return mFound;
    }
     
    //inside welcomeMessages()
    public boolean registration() {
        String username = null, password = null, contactNum = null, email = null, bankAccNum = null, subscripPlan = null;
        Member memR;
        Member tempM = new Member();
        boolean same, cont;
        
        do {        //enter username
            same = false;
            System.out.print("Username: ");
            username = scanner.nextLine();
            for (int i = 0; !same && i < numOfMem; i++) {  //check if the username had been used
                tempM = memberList.getEntry(i + 1);
                same = username.equals(tempM.getUsername());
            }
            if (same) {
                System.out.println("\nUsername has been used by others. Please enter a new username.");
            }
        } while(same);
        
        do {        //enter password
            System.out.print("Password: ");
            password = scanner.nextLine();
            if (password.length() < 6) {
                System.out.println("\nPassword must contain at least 6 characters.");
            }
        } while(password.length() < 6);
        
        do {        //enter contact number
            cont = false;
            System.out.print("Contact Number (only numbers): ");
            contactNum = scanner.nextLine();
            if (contactNum.charAt(0) != '0' || contactNum.length() < 10 || !(contactNum.matches("[0-9]+"))) {
                System.out.print("\nPlease enter a valid contact number ");
                if (contactNum.charAt(0) != '0') {
                    System.out.println("starts with \'0\'.");
                } else if (contactNum.length() < 10) {
                    System.out.println("with at least 10 digits.");
                } else if (!(contactNum.matches("[0-9]+"))) {
                    System.out.println("with only digits.");
                }
                cont = true;
            }
        } while(cont);
        String firstThree = "", other = "";     //change the format of contact number
        for (int i = 0; i < contactNum.length(); i++) {
            if (i < 3) {
                firstThree += contactNum.charAt(i);
            } else {
                other += contactNum.charAt(i);
            }
        }
        contactNum = null;
        contactNum = firstThree + "-" + other;
        
        do {        //enter email
            cont = false;
            Pattern pattern = Pattern.compile(REGEX);   //initialize the Pattern object
            System.out.print("Email (eg: xxx@gmail.com): ");
            email = scanner.nextLine();
            Matcher matcher = pattern.matcher(email);   
            if (!matcher.matches()) {
                System.out.println("\nPlease enter a valid email.");
                cont = true;
            }
        } while(cont);
        
        do {        //enter bank account number
            cont = false;
            System.out.print("Bank Account Number: ");
            bankAccNum = scanner.nextLine();
            if (!(bankAccNum.matches("[0-9]+"))) {
                System.out.println("\nPlease enter only number.");
                cont = true;
            }
        } while(cont);
        System.out.println("");
        
        do {        //choose subscripPlan
            cont = false;
            System.out.println("Which Subscription Plan would you like to choose?");
            System.out.println("Fees per month: RM50.00");
            System.out.println("[1] - 1 month (30 days), RM50.00");
            System.out.println("[2] - 3 month (90 days), RM142.50, after deducting 5% discount");
            System.out.println("[3] - 6 month (180 days), RM255.00, after deducting 15% discount");
            System.out.print("Subscription Plan (1 or 2 or 3): ");
            subscripPlan = scanner.nextLine();
            if (!(subscripPlan.equals("1")) && !(subscripPlan.equals("2")) && !(subscripPlan.equals("3"))) {
                System.out.println("\n\nPlease only enter 1 or 2 or 3.");
                cont = true;
            }
        } while(cont);
        switch (subscripPlan) {
            case "1":
                subscripPlan = "30 days";
                break;
            case "2":
                subscripPlan = "90 days";
                break;
            case "3":
                subscripPlan = "180 days";
                break;
            default:
                break;
        }
        
        memR = new Member(username, password, contactNum, email, bankAccNum, subscripPlan);
        memberList.add(memR);
        currentMember = memR;
        
        System.out.println("\n\n------------------------ Payment ------------------------");
        System.out.println("Fees per month: RM50.00");
        System.out.print("Discount: ");
        switch (currentMember.getSubscripPlan()) {
            case "30 days":
                System.out.println("none");
                break;
            case "90 days":
                System.out.println("5%");
                break;
            case "180 days":
                System.out.println("15%");
                break;
            default:
                break;
        }
        System.out.printf("Total amount deducted from " + username + "\'s bank account: RM%.2f\n", currentMember.calculateAmountPay());
        numOfMem ++;
        System.out.print("\nPress any key to proceed...");
        scanner.nextLine();
        return true;
    }
    
    public void mainPage() {
        String choice = "";
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        do {
            System.out.println("========================= Main Page =========================");
            System.out.println("[1] - Profile");
            System.out.println("[2] - Start Singing");
            System.out.println("[3] - Log out");
            System.out.print("What would you like to do? (1 or 2 or 3): ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    profile();
                    if (currentMember != null) {
                        System.out.println("\n\n\n\n\n\n");
                    }
                    break;
                case "2":
                    System.out.println("\n\nSing~~~\n\n\n\n\n\n\n");
                    break;
                case "3":
                    break;
                default: 
                    System.out.println("\n\n\n\n\n\nInvalid input. Please only enter 1 or 2 or 3.\n\n");
                    break;
            }
            if (currentMember == null) {
                choice = "3";
            }
        } while (!(choice.equals("3")));
    }
    
    //inside mainPage()
    public void profile() {
        String action = "";
        do {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n===================== Member's Profile ======================");
            System.out.println("\nID Number           : " + currentMember.getIdNum());
            System.out.println("Username            : " + currentMember.getUsername());
            System.out.println("Contact Number      : " + currentMember.getContactNum());
            System.out.println("Email address       : " + currentMember.getEmail());
            System.out.println("Bank Account Number : " + currentMember.getBankAccNum());
            System.out.println("Subscription Plan   : " + currentMember.getSubscripPlan());
            System.out.println("\n[1] - Change Password");
            System.out.println("[2] - Delete account / Stop subscription");
            System.out.println("[3] - Go back to main page");
            System.out.print("What would you like to do? (1 or 2 or 3): ");
            action = scanner.nextLine();
            switch(action) {
                case "1":
                    System.out.println("\n\n\n");
                    String oPw, nPw, msg;
                    System.out.println("-------------------- Change Password --------------------");
                    System.out.println("Please enter the following information");
                    do {
                        System.out.print("Your CURRENT password: ");
                        oPw = scanner.nextLine();
                        System.out.print("Your NEW password: ");
                        nPw = scanner.nextLine();
                        msg = currentMember.changePassword(oPw, nPw);
                        System.out.println("" + msg);
                    } while(msg.charAt(0) != 'P');
                    break;
                case "2":
                    System.out.println("\n\n");
                    String confirm;
                    System.out.println("-------------------- Delete Account ---------------------");
                    do {
                        System.out.println("Are you sure you want to delete your account? ");
                        System.out.print("(Yes or No): ");
                        confirm = scanner.nextLine();
                        confirm = confirm.toLowerCase();
                        switch (confirm) {
                            case "yes":
                                int index = memberList.contains(currentMember);
                                if (index >= 0 && index <= numOfMem) {
                                    boolean success = memberList.remove(index);
                                    if (success){
                                        System.out.println("\n\n\nAccount successfully deleted. \nThank you for using E-karaOKAY. \nHave a nice day.");
                                        numOfMem--;
                                        currentMember = null;
                                    }
                                }
                                break;
                            case "no":
                                break;
                            default:
                                System.out.println("\nPlease enter either \'Yes\' or \'No\'.");
                                break;
                        }
                    } while (!(confirm.equals("yes")) && !(confirm.equals("no")));
                    break;
                case "3":
                    System.out.println("\n\n");
                    break;
                default: 
                System.out.println("\nInvalid Input. Please only enter 1 or 2 or 3.");
                    break;
            }
        } while (!(action.equals("3")) && currentMember != null);
    }
    
    public void logOut() {
        System.out.println("\n\nThank you for using E-karaOKAY. \nHave a nice day.");
    }

    public void admin() {
        String choice;
        
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("*************************************************************");
        System.out.println("|\t\t\tE-karaOKAY Admin Page\t\t\t|");
        System.out.println("*************************************************************\n\n");
        do {
            System.out.println("-------------------------- Actions --------------------------");
            System.out.println("[1] - Add member");
            System.out.println("[2] - Remove member");
            System.out.println("[3] - Generate report");
            System.out.println("[4] - Log out");
            System.out.print("What would you like to do? (1 or 2 or 3 or 4) ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n------------------------ Add Member ------------------------");
                    System.out.println("Please enter the following information to add new member.\n");
                    registration();
                    break;
                case "2":
                    int remMem = 0;
                    boolean continueInput = true;
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    do {
                        System.out.println("----------------------- Remove Member ----------------------");
                        System.out.println("Member List: ");
                        if (numOfMem != 0) {
                            for (int i = 0; i < numOfMem; i ++) {
                                System.out.println("Member [" + (i + 1) + "] - " + memberList.getEntry(i + 1).getUsername());
                            }
                            System.out.println("Which member would you like to remove?");
                            do {
                                try {
                                    System.out.print("\n(eg: 1), (0 to quit, -1 to remove all members) \nEnter the number of the member:  ");
                                    remMem = scanner.nextInt();
                                    if ((remMem == -1) || (remMem >= 0 && remMem <= numOfMem)) {
                                        continueInput = false;
                                    } else {
                                        System.out.println("\nInvalid input. Please only enter integer between 0 to " + numOfMem + ".");
                                    }
                                }
                                catch (InputMismatchException ex){
                                    System.out.println("\nInvalid input. Please only enter integer between 0 to " + numOfMem + ".");
                                    scanner.nextLine();
                                }
                            } while (continueInput && remMem != 0);
                            scanner.nextLine();
                            if (remMem >= 1 && remMem <= numOfMem) {
                                String confirm;
                                do {
                                    System.out.println("\n\nConfirm to delete Member [" + remMem + "], " + memberList.getEntry(remMem).getUsername() + "?");
                                    System.out.print("(Yes or No): ");
                                    confirm = scanner.nextLine();
                                    confirm = confirm.toLowerCase();
                                    switch (confirm) {
                                        case "yes":
                                            boolean success = memberList.remove(remMem);
                                            if (success){
                                                System.out.println("\nAccount successfully deleted.\n\n");
                                                numOfMem--;
                                            }
                                            break;
                                        case "no":
                                            break;
                                        default:
                                            System.out.println("\nPlease enter either \'Yes\' or \'No\'.");
                                            break;
                                    }
                                } while (!(confirm.equals("yes")) && !(confirm.equals("no")));
                            } else if (remMem == -1) {
                                String confirm;
                                do {
                                    System.out.println("\n\nConfirm to delete ALL members?" );
                                    System.out.print("(Yes or No): ");
                                    confirm = scanner.nextLine();
                                    confirm = confirm.toLowerCase();
                                    switch (confirm) {
                                        case "yes":
                                            boolean success = memberList.removeAll();
                                            if (success){
                                                System.out.println("\nAll accounts successfully deleted.\n\n");
                                                numOfMem = 0;
                                            }
                                            break;
                                        case "no":
                                            break;
                                        default:
                                            System.out.println("\nPlease enter either \'Yes\' or \'No\'.");
                                            break;
                                    }
                                } while (!(confirm.equals("yes")) && !(confirm.equals("no")));
                            }
                        } else {
                            System.out.println("\n\nThere is no member left in the list. \nPress any key to proceed...");
                            scanner.nextLine();
                            remMem = 0;
                        }
                    } while (remMem != 0);
                    break;
                case "3":
                    if (numOfMem != 0) {
                        generateReport();
                    } else {
                        System.out.println("\n\nThere is no member left.");
                        System.out.print("Press any key to proceed...");
                        scanner.nextLine();
                    }
                    break;
                case "4":
                    break;
                default:
                    System.out.println("\n\n\nInvalid input. Please only enter 1 or 2 or 3 or 4.");
                    break;
            }
            System.out.println("\n\n");
        } while(!choice.equals("4"));
    }
    
    //inside admin()
    public void generateReport() {
        String order;
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        do {
            System.out.println("---------------------- Generate Report ----------------------");
            System.out.println("Report for all members in ");
            System.out.println("[1] - ascending order");
            System.out.println("[2] - descending order");
            System.out.println("[3] - quit");
            System.out.print("What would you like to do? (1 or 2 or 3) ");
            order = scanner.nextLine();
            switch (order) {
                case "1":
                    displayReport(1);
                    System.out.print("Press any key to proceed...");
                    scanner.nextLine();
                    break;
                case "2":
                    displayReport(2);
                    System.out.print("Press any key to proceed...");
                    scanner.nextLine();
                    break;
                case "3":
                    break;
                default:
                    System.out.println("\nInvalid input. Please only enter 1 or 2 or 3.");
                    break;
            }
            System.out.println("\n\n");
        } while (!order.equals("3"));
    }
    
    //inside generateReport()
    public void displayReport(int order) {
        
        System.out.println("\n\n\n\n\n\n");
        System.out.println("|----------------------------------------------------------------------------------------------------------------------|");
        if (order == 1) {
            System.out.println("|                                               Report in Ascending Order                                              |");
        } else if (order == 2) {
            System.out.println("|                                               Report in Descending Order                                             |");
        }
        System.out.println("|----------------------------------------------------------------------------------------------------------------------|");
        System.out.printf("|%10s |%10s |%10s |%15s |%20s |%20s |%20s |\n", "Id Number", "Username", "Password", "Contact Number", "Email Address", "Bank Account Number", "Subscription Plan");
        System.out.println("|----------------------------------------------------------------------------------------------------------------------|");
        System.out.print(memberList.display(order));
        System.out.println("|----------------------------------------------------------------------------------------------------------------------|");
        
    }
    
    public EkaraOKAY() {
        initialization();
        welcomeMessages();
        if (!(registered.equals(ADMIN_PASSWORD))) {
            mainPage();
            if (currentMember != null) {
                logOut();
            }
        } else {
            admin();
            logOut();
        }
    }
    
    public static void main(String[] args) {
        new EkaraOKAY();
    }
}