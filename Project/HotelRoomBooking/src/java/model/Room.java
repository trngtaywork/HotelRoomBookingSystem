/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Image;

/**
 *
 * @author My PC
 */
public class Room {
    private int RoomID;
    private String RoomName;
    private String Description;
    private double Price;
    private String Image;
    private int StatusID;
    private int TypeID;

    public Room() {
    }

    public Room(int RoomID, String RoomName, String Description, double Price, String Image, int StatusID, int TypeID) {
        this.RoomID = RoomID;
        this.RoomName = RoomName;
        this.Description = Description;
        this.Price = Price;
        this.Image = Image;
        this.StatusID = StatusID;
        this.TypeID = TypeID;
    }
    
    public Room(String RoomName, String Description, double Price, String Image, int StatusID, int TypeID) {
        this.RoomName = RoomName;
        this.Description = Description;
        this.Price = Price;
        this.Image = Image;
        this.StatusID = StatusID;
        this.TypeID = TypeID;
    }

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

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int StatusID) {
        this.StatusID = StatusID;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int TypeID) {
        this.TypeID = TypeID;
    }
    
    
}
