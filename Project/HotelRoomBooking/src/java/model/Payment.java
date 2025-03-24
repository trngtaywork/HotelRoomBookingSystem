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
public class Payment {
    private int PaymentID;
    private int BookingID;
    private int StatusID;
    private float Amount;
    private Date PaymentDate;
    private String PaymentMethod;

    public Payment() {
    }

    public Payment(int PaymentID, int BookingID, int StatusID, float Amount, Date PaymentDate, String PaymentMethod) {
        this.PaymentID = PaymentID;
        this.BookingID = BookingID;
        this.StatusID = StatusID;
        this.Amount = Amount;
        this.PaymentDate = PaymentDate;
        this.PaymentMethod = PaymentMethod;
    }
    public Payment(int BookingID, int StatusID, float Amount, Date PaymentDate, String PaymentMethod) {
        this.BookingID = BookingID;
        this.StatusID = StatusID;
        this.Amount = Amount;
        this.PaymentDate = PaymentDate;
        this.PaymentMethod = PaymentMethod;
    }

    public int getPaymentID() {
        return PaymentID;
    }

    public void setPaymentID(int PaymentID) {
        this.PaymentID = PaymentID;
    }

    public int getBookingID() {
        return BookingID;
    }

    public void setBookingID(int BookingID) {
        this.BookingID = BookingID;
    }

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int StatusID) {
        this.StatusID = StatusID;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float Amount) {
        this.Amount = Amount;
    }

    public Date getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(Date PaymentDate) {
        this.PaymentDate = PaymentDate;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String PaymentMethod) {
        this.PaymentMethod = PaymentMethod;
    }
}
