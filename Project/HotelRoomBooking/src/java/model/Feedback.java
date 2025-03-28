package model;

import java.sql.Timestamp;

public class Feedback {

    private int feedbackID;
    private int profileID;
    private int roomID;
    private String comment;
    private int rating;
    private Timestamp date;
    private String roomName;
    private String image;
    private String customerName;

    public Feedback(int feedbackID, int profileID, int roomID, String comment, int rating, Timestamp date, String roomName, String image, String customerName) {
        this.feedbackID = feedbackID;
        this.profileID = profileID;
        this.roomID = roomID;
        this.comment = comment;
        this.rating = rating;
        this.date = date;
        this.roomName = roomName;
        this.image = image;
        this.customerName = customerName;
    }

    // Getters and Setters
    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
