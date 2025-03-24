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
    private String Status;
    private String Type;

    public Room() {
    }

    public Room(int RoomID, String RoomName, String Description, double Price, String Image, String Status, String Type) {

        this.RoomID = RoomID;
        this.RoomName = RoomName;
        this.Description = Description;
        this.Price = Price;
        this.Image = Image;

        this.Status = Status;
        this.Type = Type;
    }

    public Room(String RoomName, String Description, double Price, String Image, String Status, String Type) {

        this.RoomName = RoomName;
        this.Description = Description;
        this.Price = Price;
        this.Image = Image;
        this.Status = Status;
        this.Type = Type;

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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

}
