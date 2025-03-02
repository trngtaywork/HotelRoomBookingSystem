/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author My PC
 */
public class Profile {

    private int ProfileID;
    private String Name;
    private String PhoneNumber;
    private String Gender;
    private String Role;
    private String Address;
    private int AccountID;

    public Profile() {
    }

    public Profile(int ProfileID, String Name, String PhoneNumber, String Gender, String Role, String Address, int AccountID) {
        this.ProfileID = ProfileID;
        this.Name = Name;
        this.PhoneNumber = PhoneNumber;
        this.Gender = Gender;
        this.Role = Role;
        this.Address = Address;
        this.AccountID = AccountID;
    }

    public Profile(String Name, String PhoneNumber, String Gender, String Role, String Address, int AccountID) {
        this.Name = Name;
        this.PhoneNumber = PhoneNumber;
        this.Gender = Gender;
        this.Role = Role;
        this.Address = Address;
        this.AccountID = AccountID;
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

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
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
