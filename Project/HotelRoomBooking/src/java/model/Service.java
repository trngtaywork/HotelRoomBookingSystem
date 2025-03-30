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
    private String StatusService;
    private String TypeService;

    public Service() {
    }

    public Service(int ServiceID, String ServiceName, String Description, float Price, String Image, String StatusService, String TypeService) {
        this.ServiceID = ServiceID;
        this.ServiceName = ServiceName;
        this.Description = Description;
        this.Price = Price;
        this.Image = Image;
        this.StatusService = StatusService;
        this.TypeService = TypeService;
    }

    public Service(String ServiceName, String Description, float Price, String Image, String StatusService, String TypeService) {
        this.ServiceName = ServiceName;
        this.Description = Description;
        this.Price = Price;
        this.Image = Image;
        this.StatusService = StatusService;
        this.TypeService = TypeService;
    }
    
    public Service(String ServiceName, String Description, float Price, String Image, String StatusService) {
        this.ServiceName = ServiceName;
        this.Description = Description;
        this.Price = Price;
        this.Image = Image;
        this.StatusService = StatusService;
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

    public String getStatusService() {
        return StatusService;
    }

    public void setStatusService(String StatusService) {
        this.StatusService = StatusService;
    }

    public String getTypeService() {
        return TypeService;
    }

    public void setTypeService(String TypeService) {
        this.TypeService = TypeService;
    }

}
