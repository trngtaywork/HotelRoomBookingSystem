package model;

public class Account {
    private int accountID;
    private String username;
    private String email;

    public Account(int accountID, String username, String email) {
        this.accountID = accountID;
        this.username = username;
        this.email = email;
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
}
