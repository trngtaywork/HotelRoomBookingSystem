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
public class Feedback {
    private int FeedbackID;
    private int ProfileID;
    private int RoomID;
    private String Comment;
    private int Rating;
    private Date Date;

    public Feedback() {
    }

    public Feedback(int FeedbackID, int ProfileID, int RoomID, String Comment, int Rating, Date Date) {
        this.FeedbackID = FeedbackID;
        this.ProfileID = ProfileID;
        this.RoomID = RoomID;
        this.Comment = Comment;
        this.Rating = Rating;
        this.Date = Date;
    }
    public Feedback(int ProfileID, int RoomID, String Comment, int Rating, Date Date) {
        this.ProfileID = ProfileID;
        this.RoomID = RoomID;
        this.Comment = Comment;
        this.Rating = Rating;
        this.Date = Date;
    }

    public int getFeedbackID() {
        return FeedbackID;
    }

    public void setFeedbackID(int FeedbackID) {
        this.FeedbackID = FeedbackID;
    }

    public int getProfileID() {
        return ProfileID;
    }

    public void setProfileID(int ProfileID) {
        this.ProfileID = ProfileID;
    }

    public int getRoomID() {
        return RoomID;
    }

    public void setRoomID(int RoomID) {
        this.RoomID = RoomID;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int Rating) {
        this.Rating = Rating;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }
}
