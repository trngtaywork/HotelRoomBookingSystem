package model;

import java.sql.Timestamp;

public class InvoiceDetail {
    private String customerName;
    private String username;
    private Timestamp bookingDate;
    private Timestamp startTime;
    private Timestamp endTime;
    private double totalAmount;
    private String status;

    public InvoiceDetail(String customerName, String username, Timestamp bookingDate,
                         Timestamp startTime, Timestamp endTime, double totalAmount, String status) {
        this.customerName = customerName;
        this.username = username;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public String getCustomerName() { return customerName; }
    public String getUsername() { return username; }
    public Timestamp getBookingDate() { return bookingDate; }
    public Timestamp getStartTime() { return startTime; }
    public Timestamp getEndTime() { return endTime; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
}
