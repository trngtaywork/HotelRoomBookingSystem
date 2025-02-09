/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author My PC
 */
public class Type {
    private int TypeID;
    private String TypeName;
    private String Description;

    public Type() {
    }

    public Type(int TypeID, String TypeName, String Description) {
        this.TypeID = TypeID;
        this.TypeName = TypeName;
        this.Description = Description;
    }
    
    public Type(String TypeName, String Description) {
        this.TypeName = TypeName;
        this.Description = Description;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int TypeID) {
        this.TypeID = TypeID;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    
}
