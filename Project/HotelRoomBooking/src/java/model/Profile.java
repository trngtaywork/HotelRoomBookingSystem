package model;

public class Profile {
    private int profileID;
    private String name;
    private String phoneNumber;
    private String gender;
    private String role;
    private String address;
    private int accountID;

    public Profile(int profileID, String name, String phoneNumber, String gender, String role, String address, int accountID) {
        this.profileID = profileID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.role = role;
        this.address = address;
        this.accountID = accountID;
    }

    public int getProfileID() {
        return profileID;
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

    public String getRole() {
        return role;
    }

    public String getAddress() {
        return address;
    }

    public int getAccountID() {
        return accountID;
    }
}
