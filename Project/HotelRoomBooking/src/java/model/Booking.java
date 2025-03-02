/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author My PC
 */
public class Booking {

    private int BookingID;
    private int ProfileID;
    private int RoomID;
    private Date BookingDate;
    private float TotalAmount;
    private String Status;

    public Booking() {
    }

    public Booking(int BookingID, int ProfileID, int RoomID, Date BookingDate, float TotalAmount, String Status) {
        this.BookingID = BookingID;
        this.ProfileID = ProfileID;
        this.RoomID = RoomID;
        this.BookingDate = BookingDate;
        this.TotalAmount = TotalAmount;
        this.Status = Status;
    }

    public Booking(int ProfileID, int RoomID, Date BookingDate, float TotalAmount, String Status) {
        this.ProfileID = ProfileID;
        this.RoomID = RoomID;
        this.BookingDate = BookingDate;
        this.TotalAmount = TotalAmount;
        this.Status = Status;
    }

    public int getBookingID() {
        return BookingID;
    }

    public void setBookingID(int BookingID) {
        this.BookingID = BookingID;
    }

    public int getProfileID() {
        return ProfileID;
    }

    public void setProfileID(int ProfileID) {
        this.ProfileID = ProfileID;
    }

    public int getRoomID() {
        return RoomID;
    }

    public void setRoomID(int RoomID) {
        this.RoomID = RoomID;
    }

    public Date getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(Date BookingDate) {
        this.BookingDate = BookingDate;
    }

    public float getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(float TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

}
