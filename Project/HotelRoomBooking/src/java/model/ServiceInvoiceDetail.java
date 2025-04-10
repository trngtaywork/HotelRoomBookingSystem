package model;

import java.sql.Timestamp;

public class ServiceInvoiceDetail {
    private int bookingID;
    private String customerName;
    private String username;
    private Timestamp bookingDate;
    private Timestamp startTime;
    private Timestamp endTime;
    private double totalAmount;
    private String status;
    private String serviceName;

    public ServiceInvoiceDetail(int bookingID, String customerName, String username, Timestamp bookingDate,
                                Timestamp startTime, Timestamp endTime, double totalAmount, String status, String serviceName) {
        this.bookingID = bookingID;
        this.customerName = customerName;
        this.username = username;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalAmount = totalAmount;
        this.status = status;
        this.serviceName = serviceName;
    }

    public int getBookingID() { return bookingID; }
    public String getCustomerName() { return customerName; }
    public String getUsername() { return username; }
    public Timestamp getBookingDate() { return bookingDate; }
    public Timestamp getStartTime() { return startTime; }
    public Timestamp getEndTime() { return endTime; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public String getServiceName() { return serviceName; }
}
