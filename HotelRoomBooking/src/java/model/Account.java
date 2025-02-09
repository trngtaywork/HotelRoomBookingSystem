/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Account {
    private int accountId;
    private String username;
    private String email;
    private String password;
    private String createdDate;

    public Account() {
    }

    public Account(int accountId, String username, String email, String password, String createdDate) {
        this.accountId = accountId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
    }
    public Account(int accountId, String username, String email, String password) {
    this.accountId = accountId;
    this.username = username;
    this.email = email;
    this.password = password;
}


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    
}
