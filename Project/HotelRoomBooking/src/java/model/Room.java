package model;

import java.awt.Image;

public class Room {
    private int RoomID;
    private String RoomName;
    private String Description;
    private double Price;
    private String Image;
    private String StatusRoom;
    private String TypeRoom;

    public Room() {
    }

    public Room(int RoomID, String RoomName, String Description, double Price, String Image, String StatusRoom, String TypeRoom) {
        this.RoomID = RoomID;
        this.RoomName = RoomName;
        this.Description = Description;
        this.Price = Price;
        this.Image = Image;
        this.StatusRoom = StatusRoom;
        this.TypeRoom = TypeRoom;
    }

    public Room(String RoomName, String Description, double Price, String Image, String StatusRoom, String TypeRoom) {
        this.RoomName = RoomName;
        this.Description = Description;
        this.Price = Price;
        this.Image = Image;
        this.StatusRoom = StatusRoom;
        this.TypeRoom = TypeRoom;

    }

    // Getters and setters
    public int getRoomID() {
        return RoomID;
    }

    public void setRoomID(int RoomID) {
        this.RoomID = RoomID;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String RoomName) {
        this.RoomName = RoomName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getStatusRoom() {
        return StatusRoom;
    }

    public void setStatusRoom(String StatusRoom) {
        this.StatusRoom = StatusRoom;
    }

    public String getTypeRoom() {
        return TypeRoom;
    }

    public void setTypeRoom(String TypeRoom) {
        this.TypeRoom = TypeRoom;
    }

}
