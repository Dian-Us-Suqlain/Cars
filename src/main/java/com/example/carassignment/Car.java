package com.example.carassignment;

public class Car { //Model Class for Car related Information

    private String carName;
    private String carModel;
    private String ownerName;
    private String ownerPhone;

    public Car(String carName, String carModel, String ownerName, String ownerPhone) {
        this.carName = carName;
        this.carModel = carModel;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }
}
