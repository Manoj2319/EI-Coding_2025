package com.smarthome.proxy;

import com.smarthome.model.IDevice;
import com.smarthome.exception.DeviceOperationException;
import com.smarthome.util.LoggerConfig;

import java.util.logging.Logger;


public class DeviceProxy implements IDevice {
    private final IDevice realDevice;
    private final String ownerRole;
    private final Logger logger = LoggerConfig.getLogger();

    public DeviceProxy(IDevice realDevice, String ownerRole) {
        this.realDevice = realDevice;
        this.ownerRole = ownerRole == null ? "user" : ownerRole;
    }

    public IDevice getRealDevice() { return realDevice; }

    @Override public int getId() { return realDevice.getId(); }
    @Override public String getType() { return realDevice.getType(); }
    @Override public String getStatus() { return realDevice.getStatus(); }

    private void checkAccess() throws DeviceOperationException {
        if ("blocked".equals(ownerRole)) throw new DeviceOperationException("Access denied for role: " + ownerRole);
    }

    @Override public void turnOn() throws DeviceOperationException { checkAccess(); realDevice.turnOn(); }
    @Override public void turnOff() throws DeviceOperationException { checkAccess(); realDevice.turnOff(); }
    @Override public void performAction(String action, Object... params) throws DeviceOperationException { checkAccess(); realDevice.performAction(action, params); }
}