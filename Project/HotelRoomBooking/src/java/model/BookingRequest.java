/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author My PC
 */
public class BookingRequest {
    public int BookingRequestID;
    public int BookingID;
    public String TypeBookingRequest;

    public BookingRequest() {
    }

    public BookingRequest(int BookingRequestID, int BookingID, String TypeBookingRequest) {
        this.BookingRequestID = BookingRequestID;
        this.BookingID = BookingID;
        this.TypeBookingRequest = TypeBookingRequest;
    }

    public BookingRequest(int BookingID, String TypeBookingRequest) {
        this.BookingID = BookingID;
        this.TypeBookingRequest = TypeBookingRequest;
    }

    public int getBookingRequestID() {
        return BookingRequestID;
    }

    public void setBookingRequestID(int BookingRequestID) {
        this.BookingRequestID = BookingRequestID;
    }

    public int getBookingID() {
        return BookingID;
    }

    public void setBookingID(int BookingID) {
        this.BookingID = BookingID;
    }

    public String getTypeBookingRequest() {
        return TypeBookingRequest;
    }

    public void setTypeBookingRequest(String TypeBookingRequest) {
        this.TypeBookingRequest = TypeBookingRequest;
    }
    
    
}
