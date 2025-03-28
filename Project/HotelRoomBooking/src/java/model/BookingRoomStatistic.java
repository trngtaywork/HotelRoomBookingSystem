package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BookingRoomStatistic {
    private String customerName;
    private String roomName;
    private Timestamp bookingDate;
    private double totalAmount;
    private String status;

    public BookingRoomStatistic(String customerName, String roomName, Timestamp bookingDate, double totalAmount, String status) {
        this.customerName = customerName;
        this.roomName = roomName;
        this.bookingDate = bookingDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public BookingRoomStatistic(String valueOf, double aDouble) {
    }

    public String getCustomerName() { return customerName; }
    public String getRoomName() { return roomName; }
    public Timestamp getBookingDate() { return bookingDate; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    
    public String getMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        return sdf.format(bookingDate);
    }
}
