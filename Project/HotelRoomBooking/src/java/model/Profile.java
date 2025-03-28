package model;

public class Profile {
    private int ProfileID;
    private String Name;
    private String PhoneNumber;
    private String Gender;
    private String Address;
    private int AccountID;

    public Profile(int ProfileID, String Name, String PhoneNumber, String Gender, String Address, int AccountID) {
        this.ProfileID = ProfileID;
        this.Name = Name;
        this.PhoneNumber = PhoneNumber;
        this.Gender = Gender;
        this.Address = Address;
        this.AccountID = AccountID;
    }

    public int getProfileID() {
        return ProfileID;
    }

    public String getName() {
        return Name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getGender() {
        return Gender;
    }

    public String getAddress() {
        return Address;
    }

    public int getAccountID() {
        return AccountID;
    }
}
