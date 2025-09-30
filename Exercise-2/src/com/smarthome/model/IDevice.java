package com.smarthome.model;

import com.smarthome.exception.DeviceOperationException;

public interface IDevice {
    int getId();
    String getType();
    String getStatus();
    void turnOn() throws DeviceOperationException;
    void turnOff() throws DeviceOperationException;
    void performAction(String action, Object... params) throws DeviceOperationException;
    default String getInfo() { return getType() + " (id=" + getId() + ") Status: " + getStatus(); }
}