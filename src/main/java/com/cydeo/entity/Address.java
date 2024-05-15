package com.cydeo.entity;

import com.cydeo.enums.State;

public class Address {

    private String addressInfo;
    private String phoneNumber;
    private State state;


    public Address(){

    }

    public Address(String addressInfo, String phoneNumber, State state) {
        this.addressInfo = addressInfo;
        this.phoneNumber = phoneNumber;
        this.state = state;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressInfo='" + addressInfo + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", state=" + state +
                '}';
    }
}
