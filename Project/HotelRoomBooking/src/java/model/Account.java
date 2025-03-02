/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

//import java.util.Date;
import java.sql.Date;

/**
 *
 * @author My PC
 */
public class Account {

    private int AccountID;
    private String Username;
    private String Email;
    private String Password;
    private Date CreatedDate;

    public Account() {
    }

    public Account(int AccountID, String Username, String Email, String Password, Date CreatedDate) {
        this.AccountID = AccountID;
        this.Username = Username;
        this.Email = Email;
        this.Password = Password;
        this.CreatedDate = CreatedDate;
    }

    public Account(String Username, String Email, String Password, Date CreatedDate) {
        this.Username = Username;
        this.Email = Email;
        this.Password = Password;
        this.CreatedDate = CreatedDate;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

}
