/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.SortedLinkedList;
import adt.SortedListInterface;
/**
 *
 * @author Lim Yen Qi
 */

public class Member implements Comparable<Member>{
    private static int number = 10001;
    private int idNum;
    private String username;
    private String password;
    private String contactNum;
    private String email;
    private String bankAccNum;
    private String subscripPlan;
    private SortedListInterface<Song> songList;

    public Member() {
        songList = new SortedLinkedList();
    }

    public Member(String username, String password, String contactNum, String email, String bankAccNum, String subscripPlan) {
        this.idNum = number;
        number++;
        this.username = username;
        this.password = password;
        this.contactNum = contactNum;
        this.email = email;
        this.bankAccNum = bankAccNum;
        this.subscripPlan = subscripPlan;
        songList = new SortedLinkedList();
    }

    public int getIdNum() {
        return idNum;
    }
    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNum() {
        return contactNum;
    }
    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getBankAccNum() {
        return bankAccNum;
    }
    public void setBankAccNum(String bankAccNum) {
        this.bankAccNum = bankAccNum;
    }

    public String getSubscripPlan() {
        return subscripPlan;
    }
    public void setSubscripPlan(String subsripPlan) {
        this.subscripPlan = subsripPlan;
    }
    
    public SortedListInterface<Song> getSongList() {
        return songList;
    }
    public void setSongList(SortedListInterface<Song> songList) {
        this.songList = songList;
    }
    
    @Override
    public String toString() {
        return String.format("|%10s |%10s |%10s |%15s |%20s |%20s |%20s |\n", this.idNum, this.username, this.password, this.contactNum, this.email, this.bankAccNum, this.subscripPlan);
    }
    
    @Override
    public int compareTo(Member o) {
        return username.compareTo(o.username);
    }
    
    public String changePassword(String oldPassword, String newPassword) {
        String message = "";
        if (newPassword.length() >= 6) {
            if (oldPassword.equals(this.password) && !(oldPassword.equals(newPassword))) {
                this.password = newPassword;
                message += "\nPassword successfully changed.";
            } else if (!(oldPassword.equals(this.password))) {
                message += "\nIncorrect old password.";
            } else if (oldPassword.equals(newPassword)) {
                message += "\nNew password is same as old password.";
            }
        } else {
            message += "\n\nInvalid new password. New password must contains at least 6 characters.";
        }
        return message;
    }
    
    public double calculateAmountPay () {
        double total = 0;
        final double AMOUNT_PER_MONTH = 50.00;
        switch (this.subscripPlan) {
            case "30 days":
                total += AMOUNT_PER_MONTH;
                break;
            case "90 days":
                total += (AMOUNT_PER_MONTH * 3 * 0.95);
                break;
            case "180 days":
                total += (AMOUNT_PER_MONTH * 6 * 0.85);
                break;
            default:
                break;
        }
        return total;
    }
}