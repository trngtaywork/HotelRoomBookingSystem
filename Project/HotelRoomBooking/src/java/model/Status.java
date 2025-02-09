/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author My PC
 */
public class Status {
    private int StatusID;
    private String StatusName;
    private String Description;

    public Status() {
    }

    public Status(int StatusID, String StatusName, String Description) {
        this.StatusID = StatusID;
        this.StatusName = StatusName;
        this.Description = Description;
    }
    
    public Status(String StatusName, String Description) {
        this.StatusName = StatusName;
        this.Description = Description;
    }

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int StatusID) {
        this.StatusID = StatusID;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String StatusName) {
        this.StatusName = StatusName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    
}
