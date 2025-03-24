package model;

public class Profile {
    private int profileID;
    private String name;
    private String phoneNumber;
    private String gender;
    private String address;
    private int accountID;

    public Profile(int profileID, String name, String phoneNumber, String gender, String address, int accountID) {
        this.profileID = profileID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.accountID = accountID;
    }

  public Profile(String name, String phoneNumber, String gender, String address, int accountID) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.accountID = accountID;
    }

    public int getProfileID() {
        return ProfileID;
    }

    public void setProfileID(int ProfileID) {
        this.ProfileID = ProfileID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

}
