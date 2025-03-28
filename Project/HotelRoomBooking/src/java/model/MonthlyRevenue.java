package model;

public class MonthlyRevenue {
    private int month;
    private double totalRevenue;

    public MonthlyRevenue(int month, double totalRevenue) {
        this.month = month;
        this.totalRevenue = totalRevenue;
    }

    public int getMonth() {
        return month;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }
}
