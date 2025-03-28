package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BookingServiceStatistic {
    private String customerName;
    private String serviceName;
    private Timestamp bookingDate;
    private double totalAmount;
    private String status;

    public BookingServiceStatistic(String customerName, String serviceName, Timestamp bookingDate, double totalAmount, String status) {
        this.customerName = customerName;
        this.serviceName = serviceName;
        this.bookingDate = bookingDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public BookingServiceStatistic(String valueOf, double aDouble) {
    }

    public String getCustomerName() { return customerName; }
    public String getServiceName() { return serviceName; }
    public Timestamp getBookingDate() { return bookingDate; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    
    public String getMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        return sdf.format(bookingDate);
    }
}
