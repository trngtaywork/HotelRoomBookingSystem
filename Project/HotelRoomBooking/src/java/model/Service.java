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
public class Service {
    private int ServiceID;
    private String ServiceName;
    private String Description;
    private float Price;
    private String Image;
<<<<<<< Updated upstream
    private String Status;
    private String Type;
=======
    private int StatusID;
>>>>>>> Stashed changes

    public Service() {
    }

<<<<<<< Updated upstream
    public Service(int ServiceID, String ServiceName, String Description, float Price, String Image, String Status, String Type) {
=======
    public Service(int ServiceID, String ServiceName, String Description, float Price, String Image, int StatusID) {
>>>>>>> Stashed changes
        this.ServiceID = ServiceID;
        this.ServiceName = ServiceName;
        this.Description = Description;
        this.Price = Price;
        this.Image = Image;
<<<<<<< Updated upstream
        this.Status = Status;
        this.Type = Type;
    }

    public Service(String ServiceName, String Description, float Price, String Image, String Status, String Type) {
=======
        this.StatusID = StatusID;
    }
    
    public Service(String ServiceName, String Description, float Price, String Image, int StatusID) {
>>>>>>> Stashed changes
        this.ServiceName = ServiceName;
        this.Description = Description;
        this.Price = Price;
        this.Image = Image;
<<<<<<< Updated upstream
        this.Status = Status;
        this.Type = Type;
=======
        this.StatusID = StatusID;
>>>>>>> Stashed changes
    }

    public int getServiceID() {
        return ServiceID;
    }

    public void setServiceID(int ServiceID) {
        this.ServiceID = ServiceID;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String ServiceName) {
        this.ServiceName = ServiceName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

<<<<<<< Updated upstream
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
=======
    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int StatusID) {
        this.StatusID = StatusID;
>>>>>>> Stashed changes
    }
    
    
}
