/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author My PC
 */
public class BookingServiceRequest {
    public int BookingServiceRequestID;
    public int BookingServiceID;
    public String TypeBookingServiceRequest;

    public BookingServiceRequest() {
    }

    public BookingServiceRequest(int BookingServiceRequestID, int BookingServiceID, String TypeBookingServiceRequest) {
        this.BookingServiceRequestID = BookingServiceRequestID;
        this.BookingServiceID = BookingServiceID;
        this.TypeBookingServiceRequest = TypeBookingServiceRequest;
    }

    public BookingServiceRequest(int BookingServiceID, String TypeBookingServiceRequest) {
        this.BookingServiceID = BookingServiceID;
        this.TypeBookingServiceRequest = TypeBookingServiceRequest;
    }

    public int getBookingServiceRequestID() {
        return BookingServiceRequestID;
    }

    public void setBookingServiceRequestID(int BookingServiceRequestID) {
        this.BookingServiceRequestID = BookingServiceRequestID;
    }

    public int getBookingServiceID() {
        return BookingServiceID;
    }

    public void setBookingServiceID(int BookingServiceID) {
        this.BookingServiceID = BookingServiceID;
    }

    public String getTypeBookingServiceRequest() {
        return TypeBookingServiceRequest;
    }

    public void setTypeBookingServiceRequest(String TypeBookingServiceRequest) {
        this.TypeBookingServiceRequest = TypeBookingServiceRequest;
    }
    
    
}
