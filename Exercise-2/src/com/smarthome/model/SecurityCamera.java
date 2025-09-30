package com.smarthome.model;

import com.smarthome.exception.DeviceOperationException;


public class SecurityCamera extends AbstractDevice {
    public SecurityCamera(int id) {
        super(id, "securitycamera");
        this.status = "OFF";
    }

    @Override public String getStatus() { return String.format("SecurityCamera %d is %s", id, status); }
}