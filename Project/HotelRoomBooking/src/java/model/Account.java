
package model;

import java.sql.Date;

public class Account {

    private int accountID;
    private String username;
    private String email;
    private String password;
    private String role;
    private boolean isActive;
    private Date createdDate;
    private Profile profile;

    public Account() {}
    
    public Account(int accountID, String username, String email, String role, boolean isActive, Date createdDate) {
        this.accountID = accountID;
        this.username = username;
        this.email = email;
        this.role = role;
        this.isActive = isActive;
        this.createdDate = createdDate;
    }

    public Account(String username, String email, String password, Date createdDate, String role, boolean isActive) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.role = role;
        this.isActive = isActive;
    }
    public Account(int accountID, String username, String email, Date createdDate) {
        this.accountID = accountID;
        this.username = username;
        this.email = email;
        this.createdDate = createdDate;
    }

    public Account(int accountID, String username, String email, String password, String role, boolean isActive, Date createdDate) {
        this.accountID = accountID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
        this.createdDate = createdDate;
    }
  
      public Account(int accountID, String username, String email, String password, Date createdDate, String role, boolean isActive) {
        this.accountID = accountID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.role = role;
        this.isActive = isActive;
    }

    public Account(int aInt, String string, String string0, String string1, String string2, String string3, String string4, String string5) {
    }

    public Account(String username, String email, String password, String role, boolean parseBoolean) {
    }
    
    // Getter and Setter for Profile
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    // Getters and Setters
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
   

}
