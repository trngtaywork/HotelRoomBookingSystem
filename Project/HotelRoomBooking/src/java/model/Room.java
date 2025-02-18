package model;

public class Room {
    private int roomID;
    private String roomName;
    private String description;
    private double price;
    private String image;
    private String statusRoom;
    private String typeRoom;

    // Constructor
    public Room(int roomID, String roomName, String description, double price, String image, String statusRoom, String typeRoom) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.description = description;
        this.price = price;
        this.image = image;
        this.statusRoom = statusRoom;
        this.typeRoom = typeRoom;
    }

    // Getters and Setters
    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatusRoom() {
        return statusRoom;
    }

    public void setStatusRoom(String statusRoom) {
        this.statusRoom = statusRoom;
    }

    public String getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(String typeRoom) {
        this.typeRoom = typeRoom;
    }
}
