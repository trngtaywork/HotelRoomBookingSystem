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
    private String Role;
    private boolean IsActive;

    public Account() {

    }
    
    public Account(int AccountID, String Username, String Email, String Password, Date CreatedDate, String Role, boolean IsActive) {
        this.AccountID = AccountID;
        this.Username = Username;
        this.Email = Email;
        this.Password = Password;
        this.CreatedDate = CreatedDate;
        this.Role = Role;
        this.IsActive = IsActive;
    }

    public Account(String Username, String Email, String Password, Date CreatedDate, String Role, boolean IsActive) {
        this.Username = Username;
        this.Email = Email;
        this.Password = Password;
        this.CreatedDate = CreatedDate;
        this.Role = Role;
        this.IsActive = IsActive;
    }
    
    public Account(int AccountID, String Username, String Email, Date CreatedDate) {
        this.AccountID = AccountID;
        this.Username = Username;
        this.Email = Email;
        this.CreatedDate = CreatedDate;
    }

    public Account(int AccountID, String Username, String Email) {
        this.AccountID = AccountID;
        this.Username = Username;
        this.Email = Email;
    }

    public Account(int AccountID, String Username, String Email, Date CreatedDate, String Role, boolean IsActive) {
        this.AccountID = AccountID;
        this.Username = Username;
        this.Email = Email;
        this.CreatedDate = CreatedDate;
        this.Role = Role;
        this.IsActive = IsActive;
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

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public boolean getIsActive() {
        return IsActive;
    }

    public void setIsActive(boolean IsActive) {
        this.IsActive = IsActive;
    }

    
}
