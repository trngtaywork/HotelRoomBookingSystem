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
public class BookingRoom {
    private int BookingRoomID;
    private int BookingID;
    private int RoomID;
    private int Quantity;
    private Date StartTime;
    private Date EndTime;

    public BookingRoom() {
    }

    public BookingRoom(int BookingRoomID, int BookingID, int RoomID, int Quantity, Date StartTime, Date EndTime) {
        this.BookingRoomID = BookingRoomID;
        this.BookingID = BookingID;
        this.RoomID = RoomID;
        this.Quantity = Quantity;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
    }
    public BookingRoom(int BookingID, int RoomID, int Quantity, Date StartTime, Date EndTime) {
        this.BookingID = BookingID;
        this.RoomID = RoomID;
        this.Quantity = Quantity;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
    }

    public int getBookingRoomID() {
        return BookingRoomID;
    }

    public void setBookingRoomID(int BookingRoomID) {
        this.BookingRoomID = BookingRoomID;
    }

    public int getBookingID() {
        return BookingID;
    }

    public void setBookingID(int BookingID) {
        this.BookingID = BookingID;
    }

    public int getRoomID() {
        return RoomID;
    }

    public void setRoomID(int RoomID) {
        this.RoomID = RoomID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
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
