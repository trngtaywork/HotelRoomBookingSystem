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
public class BookingService {

    private int BookingServiceID;
    private int ServiceID;
    private int BookingID;
    private int Amount;
    private Date StartTime;
    private Date EndTime;

    public BookingService() {
    }

    public BookingService(int BookingServiceID, int ServiceID, int BookingID, int Amount, Date StartTime, Date EndTime) {
        this.BookingServiceID = BookingServiceID;
        this.ServiceID = ServiceID;
        this.BookingID = BookingID;
        this.Amount = Amount;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
    }

    public BookingService(int ServiceID, int BookingID, int Amount, Date StartTime, Date EndTime) {
        this.ServiceID = ServiceID;
        this.BookingID = BookingID;
        this.Amount = Amount;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
    }

    public int getBookingServiceID() {
        return BookingServiceID;
    }

    public void setBookingServiceID(int BookingServiceID) {
        this.BookingServiceID = BookingServiceID;
    }

    public int getServiceID() {
        return ServiceID;
    }

    public void setServiceID(int ServiceID) {
        this.ServiceID = ServiceID;
    }

    public int getBookingID() {
        return BookingID;
    }

    public void setBookingID(int BookingID) {
        this.BookingID = BookingID;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date StartTime) {
        this.StartTime = StartTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date EndTime) {
        this.EndTime = EndTime;
    }

}
