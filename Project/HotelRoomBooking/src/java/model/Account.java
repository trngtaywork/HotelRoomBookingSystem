package model;

import java.sql.Date;

public class Account {

    private int accountID;
    private String username;
    private String email;
    private String role;
    private String name;
    private String phoneNumber;
    private String gender;
    private String address;
    private Date createdDate;

    public Account(int accountID, String username, String email) {
        this.accountID = accountID;
        this.username = username;
        this.email = email;
    }

    public Account(int accountID, String username, String email, String role, String name, String phoneNumber, String gender, String address) {
        this.accountID = accountID;
        this.username = username;
        this.email = email;
        this.role = role;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
    }
    
    public Account(int accountID, String username, String email, Date createdDate) {
        this.accountID = accountID;
        this.username = username;
        this.email = email;
        this.createdDate = createdDate;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }
    
       public Date getCreatedDate() {
        return createdDate;
    }
}
